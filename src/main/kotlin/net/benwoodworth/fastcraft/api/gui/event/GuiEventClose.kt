package net.benwoodworth.fastcraft.api.gui.event

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.player.Player

/**
 * An event for closed [Gui]'s.
 */
class GuiEventClose(
        /**
         * The [Gui] in which the event occurred.
         */
        val gui: Gui,

        /**
         * The [Player] whose inventory was closed.
         */
        val player: Player?
)
