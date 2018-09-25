package net.benwoodworth.fastcraft.platform.sponge.gui

import net.benwoodworth.fastcraft.platform.api.gui.GuiFactory
import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.platform.sponge.text.SpongeFcText
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetype
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.inventory.property.InventoryTitle
import javax.inject.Inject

/**
 * Sponge implementation of [GuiFactory].
 */
class SpongeGuiFactory @Inject constructor(
        private val plugin: SpongeFastCraft
) : GuiFactory {

    @Suppress("UNCHECKED_CAST")
    private fun <T : Inventory> build(
            gui: SpongeGui<*>,
            title: FcText?,
            archetype: InventoryArchetype,
            builder: Inventory.Builder.() -> Unit = {}
    ): T {
        return Inventory.builder().apply {
            of(archetype)
            forCarrier(gui)
            if (title != null) property(
                    InventoryTitle.PROPERTY_NAME,
                    InventoryTitle.of((title as SpongeFcText).base)
            )
            builder(this)
        }.build(plugin) as T
    }

    override fun chest(height: Int, title: FcText?) = SpongeGui.Chest(plugin) {
        build(it, title, InventoryArchetypes.CHEST) {
            property(
                    InventoryDimension.PROPERTY_NAME,
                    InventoryDimension.of(9, height)
            )
        }
    }

    override fun dispenser(title: FcText?) = SpongeGui.Dispenser(plugin) {
        build(it, title, InventoryArchetypes.DISPENSER)
    }

    override fun hopper(title: FcText?) = SpongeGui.Hopper(plugin) {
        build(it, title, InventoryArchetypes.HOPPER)
    }
}