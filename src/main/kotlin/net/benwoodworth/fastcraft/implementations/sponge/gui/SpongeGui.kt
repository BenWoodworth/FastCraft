package net.benwoodworth.fastcraft.implementations.sponge.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.implementations.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.implementations.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.inventory.Carrier
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.inventory.property.InventoryTitle
import org.spongepowered.api.item.inventory.type.CarriedInventory
import org.spongepowered.api.item.inventory.type.GridInventory
import org.spongepowered.api.entity.living.player.Player as Sponge_Player
import org.spongepowered.api.event.Listener as Sponge_Listener
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [Gui].
 */
class SpongeGui(
        plugin: Any,
        height: Int,
        title: Sponge_Text?
) : Gui(height), Carrier {

    private companion object {
        var registeredListeners = false
    }

    init {
        if (!registeredListeners) {
            Sponge.getEventManager().registerListeners(plugin, SpongeGuiListeners())
            registeredListeners = true
        }
    }

    /**
     * The inventory representing this GUI.
     */
    @Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private val inventory = Inventory.builder()
            .of(InventoryArchetypes.CHEST)
            .property(
                    InventoryDimension.PROPERTY_NAME,
                    InventoryDimension(width, height)
            )
            .property(
                    InventoryTitle.PROPERTY_NAME,
                    InventoryTitle(title)
            )
            .withCarrier(this)
            .build(plugin) as CarriedInventory<SpongeGui>

    private val gridInventory = inventory.query<GridInventory>(GridInventory::class.java)

    override fun getInventory() = inventory

    override val title get() = inventory.archetype // TODO Correct?
            .getProperty(InventoryTitle::class.java)
            .get().value?.let { SpongeText(it) }

    override fun open(vararg players: Player) {
        for (player in players) {
            (player as SpongePlayer).base.openInventory(inventory)
        }
    }

    override fun getViewers(): List<Player> {
        return Sponge.getServer().onlinePlayers
                .filter { it.openInventory === inventory }
                .map(::SpongePlayer)
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun updateLayout() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                val item = getButton(x, y)?.item?.mutableCopy() as SpongeItem.Mutable?
                gridInventory.set(x, y, item?.base)
            }
        }
    }
}
