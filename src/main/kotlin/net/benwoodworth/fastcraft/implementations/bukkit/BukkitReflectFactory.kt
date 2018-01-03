package net.benwoodworth.fastcraft.implementations.bukkit

import org.bukkit.Bukkit

object BukkitReflectFactory {

    private val obc = Bukkit.getServer().javaClass.`package`.name
    private val version = obc.removePrefix("org.bukkit.craftbukkit.")
    private val nms = "net.minecraft.server.$version"

    fun getNmsClass(classPath: String) = Class.forName("$nms.$classPath")

    fun getObcClass(classPath: String) = Class.forName("$obc.$classPath")
}