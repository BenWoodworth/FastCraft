package net.benwoodworth.fastcraft.dependencies.abstractions.event

import net.benwoodworth.fastcraft.dependencies.abstractions.gui.Gui
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player

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
