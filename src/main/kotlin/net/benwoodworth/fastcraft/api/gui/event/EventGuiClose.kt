package net.benwoodworth.fastcraft.api.gui.event

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.player.Player

/**
 * An event for closed [Gui]'s.
 */
class EventGuiClose(

        /**
         * The [Gui] that was closed.
         */
        val gui: Gui,

        /**
         * The [Player] whose inventory was closed.
         */
        val player: Player?
)
