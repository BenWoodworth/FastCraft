package net.benwoodworth.fastcraft.implementations.bukkit

import org.bukkit.Bukkit
import javax.inject.Singleton

@Singleton
class BukkitReflectFactory {

    private val obc = Bukkit.getServer().javaClass.`package`.name
    private val version = obc.removePrefix("org.bukkit.craftbukkit.")
    private val nms = "net.minecraft.server.$version"

    fun getNmsClass(classPath: String): Class<*> {
        return Class.forName("$nms.$classPath")
    }

    fun getObcClass(classPath: String): Class<*> {
        return Class.forName("$obc.$classPath")
    }
}