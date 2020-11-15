package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.server.FcServer
import org.bukkit.Bukkit
import org.json.simple.parser.JSONParser
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitLocalizer_1_13 @Inject constructor(
    fcPluginData: FcPluginData,
    fcLogger: FcLogger,
    fcServer: FcServer,
) : BukkitLocalizer {
    private val defaultLocale: Map<String, String>

    init {
        val langFile: URL? = Bukkit::class.java.getResource("/assets/minecraft/lang/en_us.json")
            ?: Bukkit::class.java.getResource("/assets/minecraft/lang/en_US.json")

        val overrideFile: Path =
            fcPluginData.dataFolder.resolve("minecraft-lang-override-${fcServer.minecraftVersion}.json")

        val langStream = if (Files.exists(overrideFile)) {
            fcLogger.info("Using ${overrideFile.fileName} for Minecraft localizations")
            overrideFile.toFile().inputStream()
        } else {
            langFile?.openStream()
        }

        defaultLocale = langStream
            ?.reader()
            ?.use { reader ->
                @Suppress("UNCHECKED_CAST")
                JSONParser().parse(reader) as Map<String, String>
            }
            ?: emptyMap()
    }

    override fun localize(key: String, locale: Locale): String? {
        return defaultLocale[key] // TODO Use locale
    }
}
