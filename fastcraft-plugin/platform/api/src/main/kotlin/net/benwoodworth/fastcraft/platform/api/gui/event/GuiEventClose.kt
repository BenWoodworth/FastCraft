package net.benwoodworth.fastcraft.platform.api.gui.event

import net.benwoodworth.fastcraft.platform.api.gui.FcGui
import net.benwoodworth.fastcraft.platform.api.player.FcPlayer

/**
 * An event for closed [FcGui]'s.
 *
 * @property gui The [FcGui] in which the event occurred.
 * @property player The [FcPlayer] whose inventory was closed.
 */
class GuiEventClose(
        val gui: FcGui,
        val player: FcPlayer?
)
