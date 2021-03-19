package net.benwoodworth.fastcraft.sponge.server

import net.benwoodworth.fastcraft.platform.server.FcPermission
import net.benwoodworth.fastcraft.sponge.util.asText
import org.spongepowered.api.plugin.PluginContainer
import org.spongepowered.api.service.permission.PermissionDescription
import org.spongepowered.api.service.permission.PermissionService
import javax.inject.Inject
import javax.inject.Singleton

class FcPermission_Sponge_7(
    override val description: PermissionDescription,
) : FcPermission_Sponge {
    override val name: String
        get() = description.id

    @Singleton
    class Factory @Inject constructor(
        private val permissionService: PermissionService,
        private val fastCraft: PluginContainer,
    ) : FcPermission_Sponge.Factory {
        //TODO Rename to register
        override fun create(name: String, description: String?, children: List<FcPermission>): FcPermission {
            return permissionService.newDescriptionBuilder(fastCraft)
                .id(name)
                .description(description?.asText())
                .register()
                .let { create(it) }
        }

        override fun create(description: PermissionDescription): FcPermission {
            return FcPermission_Sponge_7(description)
        }
    }
}
