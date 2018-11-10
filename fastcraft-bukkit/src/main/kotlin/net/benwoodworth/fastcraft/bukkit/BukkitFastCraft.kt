package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.FastCraft
import net.benwoodworth.fastcraft.FastCraftFactory
import org.bukkit.plugin.java.JavaPlugin

class BukkitFastCraft : JavaPlugin() {

    private val fastCraft: FastCraft

    override fun onEnable() {
        fastCraft.enable()
    }

    override fun onDisable() {
        fastCraft.disable()
    }

    init {
        val platform = Bukkit_11300R01_FcPlatformDependencies(this)

        fastCraft = FastCraftFactory(platform).create()
    }
}