package net.benwoodworth.fastcraft.dependencies.permission

/**
 * A plugin permission.
 */
interface Permission {

    /**
     * The ID of this permission.
     */
    val id: String

    /**
     * The description of what this permission is for.
     */
    val description: String

    /**
     * Builds server permissions.
     */
    interface Builder // TODO
}
