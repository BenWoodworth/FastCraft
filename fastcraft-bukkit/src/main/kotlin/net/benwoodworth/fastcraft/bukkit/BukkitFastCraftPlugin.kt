package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.FastCraft
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class BukkitFastCraftPlugin : JavaPlugin() {

    private lateinit var fastCraft: FastCraft

    override fun onEnable() {
        val factory = DaggerBukkitFastCraftFactory_1_13_00_R01.builder()
            .bukkitDaggerModule_1_13_00_R01(BukkitDaggerModule_1_13_00_R01(this))
            .build()

        fastCraft = factory.createFastCraft()

        fastCraft.enable()
    }

    override fun onDisable() {
        fastCraft.disable()
    }
}