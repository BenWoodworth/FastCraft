import kotlinx.serialization.json.json
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcText
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcTextColor
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcTextText
import net.benwoodworth.fastcraft.util.getAs

fun Bukkit_11300R01_FcText.toRaw() {
    json {
        addAdditionalJson()

        color
            ?.getAs<Bukkit_11300R01_FcTextColor>()
            ?.let { "color" to it.id }

        bold?.let { "bold" to it }
        italic?.let { "italic" to it }
        underline?.let { "underline" to it }
        strikethrough?.let { "strikethrough" to it }
        obfuscate?.let { "obfuscate" to it }

        extra
            .takeIf { it.any() }
            ?.map { it.getAs<Bukkit_11300R01_FcTextText>() }
            ?.let { "extra" to it }
    }
}