package net.benwoodworth.fastcraft.platform.gui.event

import net.benwoodworth.fastcraft.platform.gui.Gui
import net.benwoodworth.fastcraft.platform.player.FcPlayer

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
