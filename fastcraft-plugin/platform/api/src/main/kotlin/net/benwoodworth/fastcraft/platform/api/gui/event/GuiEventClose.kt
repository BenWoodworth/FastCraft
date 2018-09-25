package net.benwoodworth.fastcraft.platform.api.gui.event

import net.benwoodworth.fastcraft.platform.api.gui.Gui
import net.benwoodworth.fastcraft.platform.api.player.FcPlayer

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
