package net.benwoodworth.fastcraft.bukkit.recipe

import io.mockk.every
import io.mockk.mockk
import org.bukkit.Server
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import kotlin.test.Test
import kotlin.test.assertSame

class CraftingInventoryViewFactoryTest {
    @Test
    fun get_holder_with_use_snapshot_should_delegate_correctly() {
        val inventoryHolder = mockk<InventoryHolder>("non-snapshot")
        val inventoryHolderSnapshot = mockk<InventoryHolder>("snapshot")

        val baseInventory = mockk<Inventory>()
        every { baseInventory.getHolder(false) } returns inventoryHolder
        every { baseInventory.getHolder(true) } returns inventoryHolderSnapshot

        val server = mockk<Server>()
        every { server.createInventory(any(), InventoryType.WORKBENCH) } returns baseInventory

        val inventoryFactory = CraftingInventoryViewFactory_1_15(server)
        val customInventoryView = inventoryFactory.create(mockk(), null, null)
        val customCraftingInventory = customInventoryView.topInventory

        assertSame(inventoryHolder, customCraftingInventory.getHolder(false))
        assertSame(inventoryHolderSnapshot, customCraftingInventory.getHolder(true))
    }

}
