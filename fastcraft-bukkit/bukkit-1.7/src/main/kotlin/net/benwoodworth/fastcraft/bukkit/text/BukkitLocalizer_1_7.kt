package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcServer
import org.bukkit.Bukkit
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitLocalizer_1_7 @Inject constructor(
    fcPluginData: FcPluginData,
    fcLogger: FcLogger,
    fcServer: FcServer,
) : BukkitLocalizer {
    private val defaultLocale: Map<String, String>

    init {
        val langFile: URL? = Bukkit::class.java.getResource("/assets/minecraft/lang/en_us.lang")
            ?: Bukkit::class.java.getResource("/assets/minecraft/lang/en_US.lang")

        val overrideFile: Path =
            fcPluginData.dataFolder.resolve("minecraft-lang-override-${fcServer.minecraftVersion}.lang")

        val langStream = if (Files.exists(overrideFile)) {
            fcLogger.info("Using ${overrideFile.fileName} for Minecraft localizations")
            overrideFile.toFile().inputStream()
        } else {
            langFile?.openStream()
        }

        val entryRegex = Regex("^([^=]*)=(.*)$")

        defaultLocale = langStream
            ?.reader()
            ?.useLines { lines ->
                lines
                    .mapNotNull { entryRegex.matchEntire(it)?.destructured }
                    .map { (key, value) -> key to value }
                    .toMap()
            }
            ?: emptyMap()
    }

    override fun localize(key: String, locale: Locale): String? {
        return defaultLocale[key] // TODO Use locale
    }
}
