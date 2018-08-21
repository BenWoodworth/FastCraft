package net.benwoodworth.fastcraft.platform.impl.bukkit

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitReflectionFactory @Inject constructor(
        private val bukkitVersions: BukkitVersions
) {

    private val classCache = mutableMapOf<String, Class<*>>()

    fun getNmsClass(classPath: String): Class<*> {
        return classCache.getOrPut(classPath) {
            Class.forName("net.minecraft.server.${bukkitVersions.nmsString}.$classPath")
        }
    }

    fun getObcClass(classPath: String): Class<*> {
        return classCache.getOrPut(classPath) {
            Class.forName("org.bukkit.craftbukkit.${bukkitVersions.nmsString}.$classPath")
        }
    }
}
