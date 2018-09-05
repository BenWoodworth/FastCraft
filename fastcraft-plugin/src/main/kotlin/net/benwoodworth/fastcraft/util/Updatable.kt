package net.benwoodworth.fastcraft.util

/**
 * An object that can be updated.
 */
interface Updatable {

    /**
     * The update revision.
     */
    var revision: Int?

    /**
     * Executed after this has been updated.
     */
    fun updated()
}