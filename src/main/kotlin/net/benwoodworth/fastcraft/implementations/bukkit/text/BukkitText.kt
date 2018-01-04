package net.benwoodworth.fastcraft.implementations.bukkit.text

import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * Bukkit implementation of [Text].
 */
class BukkitText(
        /**
         * The formatted text.
         */
        val text: String
) : Text
