package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.server.FcPermission
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Permissions @Inject constructor(
    permissionFactory: FcPermission.Factory,
) {
    val FASTCRAFT_USE = permissionFactory.create(
        name = "fastcraft.use",
        description = "permission to use FastCraft for crafting",
    )

    val FASTCRAFT_COMMAND_SET_ENABLED = permissionFactory.create(
        name = "fastcraft.command.set.enabled",
        description = "Permission to use '/fastcraft set enabled'",
    )

    val FASTCRAFT_COMMAND_CRAFT_GRID = permissionFactory.create(
        name = "fastcraft.command.craft.grid",
        description = "Permission to use '/fastcraft craft grid'",
    )

    val FASTCRAFT_COMMAND_CRAFT_FASTCRAFT = permissionFactory.create(
        name = "fastcraft.command.craft.fastcraft",
        description = "Permission to use '/fastcraft craft fastcraft'",
    )

    val FASTCRAFT_ADMIN_COMMAND_SET_ENABLED = permissionFactory.create(
        name = "fastcraft.admin.command.set.enabled",
        description = "Permission to use '/fastcraft set enabled * <player>'",
    )

    val FASTCRAFT_ADMIN_COMMAND_CRAFT = permissionFactory.create(
        name = "fastcraft.admin.command.craft",
        description = "Permission to use '/fastcraft craft * <player>'",
    )

    val FASTCRAFT_COMMAND_CRAFT_ALL = permissionFactory.create(
        name = "fastcraft.command.craft.*",
        description = "All 'fastcraft.command.craft' permissions",
        children = listOf(
            FASTCRAFT_COMMAND_CRAFT_FASTCRAFT,
            FASTCRAFT_COMMAND_CRAFT_GRID,
        ),
    )

    val FASTCRAFT_COMMAND_SET_ALL = permissionFactory.create(
        name = "fastcraft.command.set.*",
        description = "All 'fastcraft.command.set' permissions",
        children = listOf(
            FASTCRAFT_COMMAND_SET_ENABLED,
        ),
    )

    val FASTCRAFT_COMMAND_ALL = permissionFactory.create(
        name = "fastcraft.command.*",
        description = "All 'fastcraft.command' permissions",
        children = listOf(
            FASTCRAFT_COMMAND_SET_ALL,
            FASTCRAFT_COMMAND_CRAFT_ALL,
        ),
    )

    val FASTCRAFT_ADMIN_COMMAND_SET_ALL = permissionFactory.create(
        name = "fastcraft.admin.command.set.*",
        description = "All 'fastcraft.admin.command.set' permissions",
        children = listOf(
            FASTCRAFT_ADMIN_COMMAND_SET_ENABLED,
        ),
    )

    val FASTCRAFT_ADMIN_COMMAND_ALL = permissionFactory.create(
        name = "fastcraft.admin.command.*",
        description = "All 'fastcraft.admin.command' permissions",
        children = listOf(
            FASTCRAFT_ADMIN_COMMAND_SET_ALL,
            FASTCRAFT_ADMIN_COMMAND_CRAFT,
        ),
    )

    val FASTCRAFT_ADMIN_ALL = permissionFactory.create(
        name = "fastcraft.admin.*",
        description = "All 'fastcraft.admin' permissions",
        children = listOf(
            FASTCRAFT_ADMIN_COMMAND_ALL,
        ),
    )

    val FASTCRAFT_ALL = permissionFactory.create(
        name = "fastcraft.*",
        description = "All 'fastcraft' permissions",
        children = listOf(
            FASTCRAFT_USE,
            FASTCRAFT_COMMAND_ALL,
            FASTCRAFT_ADMIN_ALL,
        ),
    )
}
