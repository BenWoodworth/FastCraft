package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor

/**
 * Bukkit implementation of [FcTextColor].
 */
class BukkitFcTextColor(
        codeChar: Char
) : FcTextColor {

    /**
     * The formatting code, including the section sign.
     */
    val code = "\u00A7$codeChar"
}
