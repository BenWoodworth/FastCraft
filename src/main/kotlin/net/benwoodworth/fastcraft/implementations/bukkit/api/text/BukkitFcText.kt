package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText

/**
 * Bukkit implementation of [FcText].
 *
 * @property text The formatted text.
 */
class BukkitFcText(
        val text: String
) : FcText
