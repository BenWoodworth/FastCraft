package net.benwoodworth.fastcraft.implementations.bukkit

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitReflectionFactory @Inject constructor(
        private val bukkitVersions: BukkitVersions
) {

    private val classCache = mutableMapOf<String, Class<*>>()

    fun getNmsClass(classPath: String) = classCache.getOrPut(classPath) {
        Class.forName("net.minecraft.server.${bukkitVersions.nmsString}.$classPath")
    }

    fun getObcClass(classPath: String) = classCache.getOrPut(classPath) {
        Class.forName("org.bukkit.craftbukkit.${bukkitVersions.nmsString}.$classPath")
    }
}
