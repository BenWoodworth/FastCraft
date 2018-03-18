package net.benwoodworth.fastcraft.dependencies.gui.event

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.player.FcPlayer

/**
 * An event for closed [Gui]'s.
 *
 * @property gui The [Gui] in which the event occurred.
 * @property player The [FcPlayer] whose inventory was closed.
 */
class GuiEventClose(
        val gui: Gui,
        val player: FcPlayer?
)
