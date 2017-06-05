package net.benwoodworth.fastcraft.core.dependencies.event

/**
 * An interface for cancellable events.
 */
interface FcCancellable {

    /** The cancelled state of this event. */
    var cancelled: Boolean
}
