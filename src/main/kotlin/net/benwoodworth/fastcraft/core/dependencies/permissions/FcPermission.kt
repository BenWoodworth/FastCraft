package net.benwoodworth.fastcraft.core.dependencies.permissions

/**
 * A plugin permission.
 */
enum class FcPermission(
        /** The permission [String]. */
        val permissionString: String
) {
    /** FcPermission to use FastCraft for crafting */
    USE("fastcraft.use"),

    /** FcPermission to use the `craft` command */
    COMMAND_CRAFT("fastcraft.command.craft"),

    /** FcPermission to use the `toggle` */
    COMMAND_TOGGLE("fastcraft.command.toggle"),

    /** FcPermission to use the `toggle` command on other players */
    COMMAND_TOGGLE_OTHER("fastcraft.command.toggle.other"),

    /** FcPermission to use the FastCraft `reload` command */
    ADMIN_RELOAD("fastcraft.admin.reload");
}
