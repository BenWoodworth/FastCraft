package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickModifier
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent

class BukkitFcGuiClickEvent_1_15_00_R01(
    private val event: InventoryClickEvent,
    override val button: FcGuiButton
) : BukkitFcGuiClickEvent {
    override val gui: FcGui<*>
        get() = event.view.topInventory.holder as FcGui<*>

    override val click: FcGuiClick = run {
        val modifiers = mutableSetOf<FcGuiClickModifier>()
            .apply {
                if (event.isShiftClick) {
                    add(FcGuiClickModifier.Shift)
                }

                if (event.click == ClickType.CONTROL_DROP) {
                    add(FcGuiClickModifier.Control)
                }

                if (event.click == ClickType.DOUBLE_CLICK) {
                    add(FcGuiClickModifier.DoubleClick)
                }
            }
            .toSet()

        when {
            event.isLeftClick -> FcGuiClick.Primary(modifiers)
            event.isRightClick -> FcGuiClick.Secondary(modifiers)
            event.click == ClickType.MIDDLE -> FcGuiClick.Middle(modifiers)
            event.click == ClickType.DROP || event.click == ClickType.CONTROL_DROP -> FcGuiClick.Drop(modifiers)
            event.hotbarButton != -1 -> FcGuiClick.Number(event.hotbarButton, modifiers)
            else -> throw IllegalStateException("Unable to read click: ${event.click}")
        }
    }
}
