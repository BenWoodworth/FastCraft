//package net.benwoodworth.fastcraft.bukkit.dependencies.event.events
//
//import net.benwoodworth.fastcraft.bukkit.dependencies.player.BukkitPlayerAdapter
//import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
//import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter
//import org.bukkit.event.player.PlayerJoinEvent
//
///**
// * Created by ben on 6/5/17.
// */
//class BukkitEventPlayerJoin(
//        private val base: PlayerJoinEvent
//) : FcEventPlayerJoin {
//
//    override val player: PlayerAdapter
//        get() = BukkitPlayerAdapter(base.player)
//}
