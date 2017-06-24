package net.benwoodworth.fastcraft.core.dependencies.permission

/**
 * A plugin permission.
 */
interface Permission {

    /** The ID of this permission. */
    val id: String

    /** The description of what this permission is for */
    val description: String
}
