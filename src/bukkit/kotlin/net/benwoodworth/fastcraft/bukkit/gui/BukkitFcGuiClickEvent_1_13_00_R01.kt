package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent

class BukkitFcGuiClickEvent_1_13_00_R01(
    private val event: InventoryClickEvent,
    override val button: FcGuiButton
) : BukkitFcGuiClickEvent {

    override val gui: FcGui<*>
        get() = event.view.topInventory.holder as FcGui<*>

    override val isPrimaryClick: Boolean
        get() = event.isLeftClick

    override val isSecondaryClick: Boolean
        get() = event.isRightClick

    override val isMiddleClick: Boolean
        get() = event.click == ClickType.MIDDLE

    override val isDoubleClick: Boolean
        get() = event.click == ClickType.DOUBLE_CLICK

    override val isShiftClick: Boolean
        get() = event.isShiftClick

    override val isControlClick: Boolean
        get() = event.click == ClickType.CONTROL_DROP

    override val isDropClick: Boolean
        get() = when (event.click) {
            ClickType.DROP,
            ClickType.CONTROL_DROP -> true
            else -> false
        }

    override val numberKeyPressed: Int?
        get() = event.hotbarButton.takeUnless { it == -1 }
}
