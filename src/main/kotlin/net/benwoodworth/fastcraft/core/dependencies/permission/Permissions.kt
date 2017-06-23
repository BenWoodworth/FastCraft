package net.benwoodworth.fastcraft.core.dependencies.permission

/**
 * A plugin permission.
 */
enum class Permissions(
        /** The permission [String]. */
        val permissionString: String
) {
    /** PermissionAdapter to use FastCraft for crafting */
    USE("fastcraft.use"),

    /** PermissionAdapter to use the `craft` command */
    COMMAND_CRAFT("fastcraft.command.craft"),

    /** PermissionAdapter to use the `toggle` */
    COMMAND_TOGGLE("fastcraft.command.toggle"),

    /** PermissionAdapter to use the `toggle` command on other players */
    COMMAND_TOGGLE_OTHER("fastcraft.command.toggle.other"),

    /** PermissionAdapter to use the FastCraft `reload` command */
    ADMIN_RELOAD("fastcraft.admin.reload");
}
