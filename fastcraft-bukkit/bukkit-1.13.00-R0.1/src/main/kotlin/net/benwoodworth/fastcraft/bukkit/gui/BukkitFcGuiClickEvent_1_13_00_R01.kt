package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.player.FcPlayer

class BukkitFcGuiClickEvent_1_13_00_R01(
    override val gui: FcGui<*>,
    override val player: FcPlayer,
    override val isPrimaryClick: Boolean,
    override val isSecondaryClick: Boolean,
    override val isMiddleClick: Boolean,
    override val isDoubleClick: Boolean,
    override val isShiftClick: Boolean,
    override val numberKeyPressed: Int?
) : BukkitFcGuiClickEvent
