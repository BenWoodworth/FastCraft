package net.benwoodworth.fastcraft.core.dependencies.event

/**
 * An interface for cancellable events.
 */
interface Cancellable {
    /** The cancelled state of this event. */
    var cancelled: Boolean
}
