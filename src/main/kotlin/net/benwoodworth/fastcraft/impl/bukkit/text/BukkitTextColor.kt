package net.benwoodworth.fastcraft.impl.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.TextColor

/**
 * Bukkit implementation of [TextColor].
 */
class BukkitTextColor(
        /**
         * The Minecraft formatting code character.
         *
         * [https://minecraft.gamepedia.com/Formatting_codes]
         */
        val codeChar: Char
) : TextColor {

    /**
     * The formatting code, including the section sign.
     */
    val code = "\u00A7$codeChar"
}
