package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPermission
import org.bukkit.permissions.Permission

interface FcPermission_Bukkit : FcPermission {
    val permission: Permission

    interface Factory : FcPermission.Factory {
        fun create(permission: Permission): FcPermission
    }
}

val FcPermission.permission: Permission
    get() = (this as FcPermission_Bukkit).permission

fun FcPermission.Factory.create(permission: Permission): FcPermission {
    return (this as FcPermission_Bukkit.Factory).create(permission)
}
