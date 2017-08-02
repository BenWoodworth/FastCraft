package net.benwoodworth.fastcraft.dependencies.event

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.player.Player

/**
 * An event for closed [Gui]'s.
 */
interface EventGuiClose {

    /**
     * The [Gui] that was closed.
     */
    val gui: Gui

    /**
     * The [Player] whose inventory was closed.
     */
    val player: Player?

    class Impl(
            override val gui: Gui,
            override val player: Player?
    ) : EventGuiClose
}
