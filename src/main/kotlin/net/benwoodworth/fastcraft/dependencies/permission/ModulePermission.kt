package net.benwoodworth.fastcraft.dependencies.permission

/**
 * Dagger module for permission dependencies.
 */
interface ModulePermission {

    fun permissionBuilder(): Permission.Builder
}
