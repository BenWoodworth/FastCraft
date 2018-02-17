package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.Text

/**
 * Bukkit implementation of [Text].
 */
class BukkitText(
        /**
         * The formatted text.
         */
        val text: String
) : Text
