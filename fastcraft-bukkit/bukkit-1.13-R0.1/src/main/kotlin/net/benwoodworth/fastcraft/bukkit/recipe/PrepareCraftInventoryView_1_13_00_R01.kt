package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.Recipe
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy
import javax.inject.Inject

abstract class PrepareCraftInventoryView_1_13_00_R01 : PrepareCraftInventoryView() {
    class Factory @Inject constructor(
        private val server: Server
    ) : PrepareCraftInventoryView_1_15_00_R01.Factory(server) {
        private val getPlayer = InventoryView::class.java.getMethod(
            InventoryView::getPlayer.name
        )

        private val getType = InventoryView::class.java.getMethod(
            InventoryView::getType.name
        )

        private val getBottomInventory = InventoryView::class.java.getMethod(
            InventoryView::getPlayer.name
        )

        private val getTopInventory = InventoryView::class.java.getMethod(
            InventoryView::getPlayer.name
        )

        override fun create(
            player: Player,
            inventoryHolder: InventoryHolder?,
            recipe: Recipe?
        ): PrepareCraftInventoryView {
            val topInventory = PreparedInventory(
                inventory = server.createInventory(inventoryHolder, InventoryType.WORKBENCH),
                recipe = recipe
            )

            val invocationHandler = InvocationHandler { proxy, method, args ->
                return@InvocationHandler when (method) {
                    getPlayer -> player
                    getType -> topInventory.type
                    getBottomInventory -> player.inventory
                    getTopInventory -> topInventory
                    else -> method.invoke(proxy, *args)
                }
            }

            val proxy = Proxy.newProxyInstance(
                this::class.java.classLoader,
                emptyArray(),
                invocationHandler
            )

            return proxy as PrepareCraftInventoryView_1_13_00_R01
        }
    }
}