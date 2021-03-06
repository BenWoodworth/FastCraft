package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPermission
import org.bukkit.permissions.Permission
import org.bukkit.plugin.PluginManager
import javax.inject.Inject
import javax.inject.Singleton

class FcPermission_Bukkit_1_7(
    override val permission: Permission,
) : FcPermission_Bukkit {
    override val name: String
        get() = permission.name

    @Singleton
    class Factory @Inject constructor(
        private val pluginManager: PluginManager,
    ) : FcPermission_Bukkit.Factory {
        override fun create(name: String, description: String?, children: List<FcPermission>): FcPermission {
            val childrenMap = children
                .map { it.name to true }
                .toMap()

            if (pluginManager.getPermission(name) != null) {
                pluginManager.removePermission(name)
            }

            val permission = Permission(name, description, childrenMap)
            pluginManager.addPermission(permission)

            return create(permission)
        }

        override fun create(permission: Permission): FcPermission {
            return FcPermission_Bukkit_1_7(permission)
        }
    }
}
