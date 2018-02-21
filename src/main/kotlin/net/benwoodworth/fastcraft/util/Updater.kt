package net.benwoodworth.fastcraft.util

/**
 * A class to update objects, such as configurations.
 */
abstract class Updater<in T : Updatable>(
        vararg val updates: T.() -> Unit
) {

    /**
     * Updates an object.
     *
     * @param updatable the object to be updated.
     */
    fun update(updatable: T) {
        var revision = updatable.revision ?: -1
        val oldRevision = revision

        if (revision < 0) {
            initialize(updatable)
            revision = 0
        }

        while (revision < updates.size) {
            updates[revision++](updatable)
        }

        if (revision != oldRevision) {
            updatable.revision = revision
            finalize(updatable)
        }
    }

    /**
     * Code to be run for objects that aren't setup.
     *
     * @param updatable the object to initialize.
     */
    abstract fun initialize(updatable: T)

    /**
     * Code to be run after updates have been made.
     *
     * @param updatable the object to finalize.
     */
    abstract fun finalize(updatable: T)
}