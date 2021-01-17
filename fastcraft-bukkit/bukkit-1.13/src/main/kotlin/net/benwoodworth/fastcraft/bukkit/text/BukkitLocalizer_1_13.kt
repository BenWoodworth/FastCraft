package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.util.MinecraftAssets
import org.bukkit.Bukkit
import java.net.URL
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitLocalizer_1_13 @Inject constructor(
    private val fcLogger: FcLogger,
    private val fcTaskFactory: FcTask.Factory,
    private val assetDownloader: MinecraftAssets,
    private val fcPlayerProvider: FcPlayer.Provider,
) : BukkitLocalizer {

    private val defaultLocale: HashMap<String, String>
    private val loadedLocales = ConcurrentHashMap<Locale, Map<String, String>>()
    private val availableLocales = ConcurrentHashMap<Locale, String>()

    init {
        val langFile: URL? = Bukkit::class.java.getResource("/assets/minecraft/lang/en_us.json")
            ?: Bukkit::class.java.getResource("/assets/minecraft/lang/en_US.json")

        defaultLocale = langFile?.openStream()?.reader()
            ?.use { reader -> Json.decodeFromString(reader.readText()) }
            ?: hashMapOf()

        fcTaskFactory.startTask(async = true) {
            loadLocaleAssets()
        }
    }

    private val Locale.asset: String?
        get() = availableLocales[this]?.let { "minecraft/lang/$it.json" }

    private fun loadLocaleAssets() {
        val localeAssetRegex = Regex("^minecraft/lang/(.*).json$")

        assetDownloader.getAssets()
            .mapNotNull { localeAssetRegex.matchEntire(it) }
            .forEach { match ->
                try {
                    availableLocales.keys.forEach {
                        assetDownloader.cacheAsset(it.asset!!)
                    }

                    val bukkitLocaleCode = match.groupValues[1]
                    val locale = bukkitLocaleCode.toLocale()
                    availableLocales[locale] = bukkitLocaleCode
                } catch (e: Exception) {
                    fcLogger.error("Unable to cache asset '${match.value}': ${e.message}")
                }
            }
    }

    private fun loadLocale(locale: Locale) {
        val asset = locale.asset ?: return

        try {
            val langJson = assetDownloader.openAsset(asset).use { it.bufferedReader().readText() }
            loadedLocales[locale] = Json.decodeFromString<HashMap<String, String>>(langJson)
        } catch (e: Exception) {
            fcLogger.error("Unable to load asset '$asset': ${e.message}")
        }
    }

    override fun localize(key: String, locale: Locale): String? {
        val selectedLocale = when {
            !availableLocales.containsKey(locale) -> defaultLocale
            !loadedLocales.containsKey(locale) -> {
                loadLocale(locale)
                loadedLocales[locale] ?: defaultLocale
            }
            else -> loadedLocales[locale] ?: defaultLocale
        }

        return selectedLocale[key] ?: defaultLocale[key]
    }

    private fun String.toLocale(): Locale {
        val parts = split('_', '-', limit = 2)
        return Locale(
            parts.first().toLowerCase(),
            parts.getOrNull(1)?.toUpperCase() ?: "",
        )
    }
}
