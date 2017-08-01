package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import org.spongepowered.api.Sponge
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.Slot
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory
import org.spongepowered.api.item.inventory.crafting.CraftingInventory
import org.spongepowered.api.item.inventory.crafting.CraftingOutput
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.inventory.type.GridInventory
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe
import org.spongepowered.api.world.World
import java.util.Optional

/**
 * A custom implementation of [CraftingInventory].
 */
internal class CustomSpongeCraftingInventory(
        plugin: Any
) : CraftingGridInventory, CraftingInventory, GridInventory by Inventory.builder()
        .of(InventoryArchetypes.WORKBENCH)
        .property(
                InventoryDimension.PROPERTY_NAME,
                InventoryDimension(3, 3)
        )
        .build(plugin)
        .query<GridInventory>(GridInventory::class.java)
{

    /**
     * The result slot.
     */
    private val resultSlot = CustomCraftingOutput(plugin)

    override fun getRecipe(world: World?): Optional<CraftingRecipe> {
        return Sponge.getRegistry().craftingRecipeRegistry.findMatchingRecipe(this, world)
    }

    override fun getCraftingGrid(): CraftingGridInventory {
        return this
    }

    override fun getResult(): CraftingOutput {
        return resultSlot
    }

    private class CustomCraftingOutput(
            plugin: Any
    ) : CraftingOutput, Slot by Inventory.builder()
            .property(InventoryDimension.PROPERTY_NAME, InventoryDimension(1, 1))
            .build(plugin)
            .query<GridInventory>(GridInventory::class.java)
            .getSlot(0, 0).get()
}
