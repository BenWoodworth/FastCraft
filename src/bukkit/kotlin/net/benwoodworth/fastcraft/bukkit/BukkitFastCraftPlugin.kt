package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.FastCraft
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class BukkitFastCraftPlugin : JavaPlugin() {
    private var fastCraft: FastCraft? = null

    override fun onEnable() {
        val factory = DaggerBukkitFastCraftFactory_1_15_00_R01.builder()
            .bukkitDaggerModule_1_15_00_R01(BukkitDaggerModule_1_15_00_R01(this))
            .build()

        fastCraft = factory.createFastCraft()
    }

    override fun onDisable() {
        fastCraft!!.disable()
        fastCraft = null
    }

    override fun reloadConfig() {
        super.reloadConfig()
        // TODO
    }
}
