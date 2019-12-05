package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcLocale
import org.bukkit.Bukkit
import javax.inject.Inject

class BukkitLocalizer_1_13_00_R01 @Inject constructor(
) : BukkitLocalizer {
    private val defaultLocale: Map<String, String>

    init {
        val entryRegex = Regex(""".*?"(.*)"\s*:\s*"(.*)".*""")

        defaultLocale = Bukkit::class.java
            .getResource("/assets/minecraft/lang/en_us.json")
            ?.openStream()
            ?.reader()
            ?.useLines { lines ->
                lines
                    .mapNotNull { entryRegex.matchEntire(it)?.destructured }
                    .map { (key, value) -> key to value }
                    .toMap()
            }
            ?: emptyMap()
    }

    override fun localize(key: String, locale: FcLocale): String? {
        return defaultLocale[key] // TODO Use locale
    }
}
