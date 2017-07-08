package net.benwoodworth.fastcraft.util

/**
 * An interface for all things cancellable.
 */
interface Cancellable {

    /** Whether this action is cancelled. */
    var cancelled: Boolean
}
