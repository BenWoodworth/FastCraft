package net.benwoodworth.fastcraft.core.dependencies.permission

import net.benwoodworth.fastcraft.core.dependencies.text.Text

/**
 * A plugin permission.
 */
interface Permission {

    /** The ID of this permission. */
    val id: String

    /** The description of what this permission is for */
    val description: Text
}
