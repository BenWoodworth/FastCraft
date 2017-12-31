package net.benwoodworth.fastcraft.implementations.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.TextColor

/**
 * Bukkit implementation of [TextColor].
 */
class BukkitTextColor(
        codeChar: Char
) : TextColor {

    /**
     * The formatting code, including the section sign.
     */
    val code = "\u00A7$codeChar"
}
