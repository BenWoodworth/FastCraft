package net.benwoodworth.fastcraft.dependencies.api.gui.event

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer

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
