package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.Text

/**
 * Bukkit implementation of [Text].
 *
 * @property text The formatted text.
 */
class BukkitText(
        val text: String
) : Text
