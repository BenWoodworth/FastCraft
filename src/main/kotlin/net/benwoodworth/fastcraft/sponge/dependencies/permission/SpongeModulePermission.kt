package net.benwoodworth.fastcraft.sponge.dependencies.permission

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.core.dependencies.permission.ModulePermission
import net.benwoodworth.fastcraft.core.dependencies.permission.PermissionBuilder

/**
 * Sponge implementation of [ModulePermission].
 */
@Module
class SpongeModulePermission : ModulePermission {

    @Provides
    override fun permissionBuilder(): PermissionBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
