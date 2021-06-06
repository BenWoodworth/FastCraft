package net.benwoodworth.fastcraft.bukkit.text

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.util.MinecraftAssets
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLocaleChangeEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import java.net.URL
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitLocalizer_1_13 @Inject constructor(
    private val fcLogger: FcLogger,
    private val fcTaskFactory: FcTask.Factory,
    private val assets: MinecraftAssets,
    pluginManager: PluginManager,
    plugin: Plugin,
) : BukkitLocalizer {
    private val defaultLocale: HashMap<String, String>
    private val loadedLocales = HashMap<Locale, HashMap<String, String>>()
    private val availableLocales = HashMap<Locale, String>()

    init {
        val langFile: URL? = Bukkit::class.java.getResource("/assets/minecraft/lang/en_us.json")
            ?: Bukkit::class.java.getResource("/assets/minecraft/lang/en_US.json")

        defaultLocale = langFile?.openStream()?.reader()
            ?.use { reader -> Json.decodeFromString(reader.readText()) }
            ?: hashMapOf()

        loadAvailableLocales()

        pluginManager.registerEvents(EventListener(), plugin)
    }

    private fun loadAvailableLocales() {
        fcTaskFactory.startTask(async = true) {
            val localeAssetRegex = Regex("""minecraft/lang/(.*)\.json""")

            val localeAssets = assets.getAssets()
                .mapNotNull { localeAssetRegex.matchEntire(it) }

            fcTaskFactory.startTask {
                localeAssets.forEach { match ->
                    val bukkitLocaleCode = match.groupValues[1]
                    val locale = bukkitLocaleCode.toLocale()

                    availableLocales[locale] = bukkitLocaleCode
                }
            }
        }
    }

    private fun loadLocale(locale: Locale) {
        if (loadedLocales.containsKey(locale) || !availableLocales.containsKey(locale)) return
        loadedLocales[locale] = hashMapOf() // Prevent trying to load when already loading

        fcTaskFactory.startTask(async = true) {
            val localeContent = try {
                assets.openAsset("minecraft/lang/${availableLocales[locale]}.json")
                    .use { it.bufferedReader().readText() }
                    .let { Json.decodeFromString<HashMap<String, String>>(it) }
            } catch (e: Exception) {
                fcLogger.error("Unable to load locale '$locale': ${e.message}")
                hashMapOf()
            }

            fcTaskFactory.startTask {
                loadedLocales[locale] = localeContent
            }
        }
    }

    override fun localize(key: String, locale: Locale): String? {
        loadLocale(locale)
        return loadedLocales[locale]?.get(key) ?: defaultLocale[key]
    }

    private fun String.toLocale(): Locale {
        val parts = split('_', '-', limit = 2)
        return Locale(
            parts.first().toLowerCase(),
            parts.getOrNull(1)?.toUpperCase() ?: "",
        )
    }

    private inner class EventListener : Listener {
        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) {
            loadLocale(event.player.locale.toLocale())
        }

        @EventHandler
        fun onPlayerLocaleChange(event: PlayerLocaleChangeEvent) {
            loadLocale(event.locale.toLocale())
        }
    }
}
