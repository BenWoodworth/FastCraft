package net.benwoodworth.fastcraft.implementations.sponge.api.gui

import net.benwoodworth.fastcraft.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetype
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.inventory.property.InventoryTitle
import javax.inject.Inject
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [GuiFactory].
 */
class SpongeGuiFactory @Inject constructor(
        private val plugin: SpongeFastCraft
) : GuiFactory {

    @Suppress("UNCHECKED_CAST")
    private fun <T : Inventory> build(
            gui: SpongeGui<*>,
            title: Text?,
            archetype: InventoryArchetype,
            builder: Inventory.Builder.() -> Unit = {}
    ): T {
        return Inventory.builder().apply {
            of(archetype)
            forCarrier(gui)
            if (title != null) property(
                    InventoryTitle.PROPERTY_NAME,
                    InventoryTitle.of((title as SpongeText).base)
            )
            builder(this)
        }.build(plugin) as T
    }

    override fun chest(height: Int, title: Text?) = SpongeGui.Chest(plugin) {
        build(it, title, InventoryArchetypes.CHEST) {
            property(
                    InventoryDimension.PROPERTY_NAME,
                    InventoryDimension.of(9, height)
            )
        }
    }

    override fun dispenser(title: Text?) = SpongeGui.Dispenser(plugin) {
        build(it, title, InventoryArchetypes.DISPENSER)
    }

    override fun hopper(title: Text?) = SpongeGui.Hopper(plugin) {
        build(it, title, InventoryArchetypes.HOPPER)
    }
}