//package net.benwoodworth.fastcraft.bukkit.dependencies.player
//
//import net.benwoodworth.fastcraft.core.dependencies.inventory.InventoryAdapter
//import net.benwoodworth.fastcraft.core.dependencies.permissions.PermissionAdapter
//import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter
//import org.bukkit.entity.Player
//import org.bukkit.inventory.Inventory
//import java.util.*
//
///**
// * Adapter for Bukkit players.
// */
//class BukkitPlayerAdapter(
//        private val basePlayer: Player
//): PlayerAdapter(basePlayer) {
//
//    override val username: String
//        get() = basePlayer.name
//
//    override var displayName: String?
//        get() = basePlayer.displayName
//        set(value) {
//            basePlayer.displayName = value
//        }
//
//    override val uuid: UUID
//        get() = basePlayer.uniqueId
//
//    override fun sendMessage(message: String) {
//        basePlayer.sendMessage(message)
//    }
//
//    override fun hasPermission(permission: PermissionAdapter): Boolean {
//        return basePlayer.hasPermission(permission.permissionString)
//    }
//
//    override fun openInventory(inventory: InventoryAdapter) {
//        basePlayer.openInventory(inventory.unwrap<Inventory>())
//    }
//}
