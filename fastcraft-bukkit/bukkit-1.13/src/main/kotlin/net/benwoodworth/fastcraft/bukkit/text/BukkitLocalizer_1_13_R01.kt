package net.benwoodworth.fastcraft.bukkit.text

import org.bukkit.Bukkit
import org.json.simple.parser.JSONParser
import java.util.*
import javax.inject.Inject

class BukkitLocalizer_1_13_R01 @Inject constructor(
) : BukkitLocalizer {
    private val defaultLocale: Map<String, String> =
        Bukkit::class.java
            .getResource("/assets/minecraft/lang/en_us.json")
            ?.openStream()
            ?.reader()
            ?.use { reader ->
                @Suppress("UNCHECKED_CAST")
                JSONParser().parse(reader) as Map<String, String>
            }
            ?: emptyMap()

    override fun localize(key: String, locale: Locale): String? {
        return defaultLocale[key] // TODO Use locale
    }
}
