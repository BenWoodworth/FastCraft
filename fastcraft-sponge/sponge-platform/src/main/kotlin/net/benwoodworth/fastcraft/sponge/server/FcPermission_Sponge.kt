package net.benwoodworth.fastcraft.sponge.server

import net.benwoodworth.fastcraft.platform.server.FcPermission
import org.spongepowered.api.service.permission.PermissionDescription

interface FcPermission_Sponge : FcPermission {
    val description: PermissionDescription

    interface Factory : FcPermission.Factory {
        fun create(description: PermissionDescription): FcPermission
    }
}

val FcPermission.description: PermissionDescription
    get() = (this as FcPermission_Sponge).description

fun FcPermission.Factory.create(description: PermissionDescription): FcPermission {
    return (this as FcPermission_Sponge.Factory).create(description)
}
