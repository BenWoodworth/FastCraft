package net.benwoodworth.fastcraft.util

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcServer
import java.io.InputStream
import java.net.URL
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.security.DigestInputStream
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.io.path.*

@Singleton
@OptIn(ExperimentalPathApi::class)
class MinecraftAssets @Inject constructor(
    fcPluginData: FcPluginData,
    private val fcServer: FcServer,
    private val fcLogger: FcLogger,
) {
    private val versionManifestUrl = URL("https://launchermeta.mojang.com/mc/game/version_manifest.json")
    private val assetsCacheDir = fcPluginData.dataFolder.resolve("cache/assets/minecraft/${fcServer.minecraftVersion}")
    private lateinit var assetIndex: AssetIndex

    private val json = Json {
        this.ignoreUnknownKeys = true
    }

    private fun getVersionManifest(): VersionManifest {
        versionManifestUrl.openStream().bufferedReader().use { reader ->
            return json.decodeFromString(reader.readText())
        }
    }

    private fun getVersionInfo(): VersionInfo {
        val versionInfoUrl = getVersionManifest().versions
            .firstOrNull { it.id == fcServer.minecraftVersion }
            ?.let { URL(it.url) }
            ?: error("Minecraft version not found in version manifest: ${fcServer.minecraftVersion}")

        versionInfoUrl.openStream().bufferedReader().use { reader ->
            return json.decodeFromString(reader.readText())
        }
    }

    private fun getAssetIndex(): AssetIndex {
        if (!this::assetIndex.isInitialized) {
            assetIndex = try {
                val versionInfo = getVersionInfo()

                val assetIndexFile = assetsCacheDir.resolve("index.json")
                if (!assetIndexFile.exists()) {
                    downloadFile(URL(versionInfo.assetIndex.url), assetIndexFile, versionInfo.assetIndex.sha1)
                }

                json.decodeFromString(assetIndexFile.readText())
            } catch (e: Exception) {
                fcLogger.error("Error getting Minecraft assets: ${e.message}")
                AssetIndex(emptyMap())
            }
        }

        return assetIndex
    }

    fun getAssets(): Set<String> {
        return getAssetIndex().objects.keys
    }

    private fun cacheAsset(name: String) {
        val assetCacheFile = assetsCacheDir.resolve(name)

        if (!assetCacheFile.exists()) {
            val asset = getAssetIndex().objects[name] ?: error("Minecraft asset not found: $name")
            downloadFile(asset.url, assetCacheFile, asset.hash)
        }
    }

    fun openAsset(name: String): InputStream {
        cacheAsset(name)
        return assetsCacheDir.resolve(name).inputStream()
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun downloadFile(url: URL, file: Path, sha1: String?) {
        val partFile = file.resolveSibling("${file.fileName}.part")
        if (!partFile.parent.exists()) {
            partFile.parent.createDirectories()
        } else if (partFile.exists()) {
            partFile.deleteExisting()
        }

        val digest = MessageDigest.getInstance("SHA-1")
        DigestInputStream(url.openStream(), digest).bufferedReader().use { reader ->
            partFile.bufferedWriter().use { writer ->
                reader.copyTo(writer)
            }
        }

        if (sha1 != null) {
            val actualHash = digest.digest().asUByteArray()
                .joinToString("") {
                    it.toString(16).padStart(2, '0')
                }

            if (!actualHash.equals(sha1, ignoreCase = true)) {
                error("Minecraft asset hash mismatch while downloading $url")
            }
        }

        partFile.moveTo(file, StandardCopyOption.REPLACE_EXISTING)
    }

    @Serializable
    private class VersionManifest(
        val versions: List<Version>,
    ) {
        @Serializable
        class Version(
            val id: String,
            val url: String,
        )
    }

    @Serializable
    private class VersionInfo(
        val assetIndex: AssetIndex,
    ) {
        @Serializable
        class AssetIndex(
            val id: String,
            val sha1: String,
            val size: Long,
            val url: String,
        )
    }

    @Serializable
    private class AssetIndex(
        val objects: Map<String, Object>,
    ) {
        @Serializable
        class Object(
            val hash: String,
            val size: Long,
        )
    }

    private val AssetIndex.Object.url: URL
        get() = URL("https://resources.download.minecraft.net/${hash.take(2)}/$hash")
}
