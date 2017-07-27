package net.benwoodworth.fastcraft.dependencies.event

/**
 * Listens to events, and notifies event handlers when events occur.
 *
 * @param TEvent the type of event being listened for
 */
interface Listener<TEvent> {

    /**
     * Raise an event.
     *
     * @param event the event to raise
     */
    fun notifyHandlers(event: TEvent)

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    fun addHandler(handler: (event: TEvent) -> Unit)

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    fun addHandler(handler: () -> Unit)

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    operator fun plusAssign(handler: (event: TEvent) -> Unit)

    /**
     * Add an event handler to this listener.
     *
     * @param handler the event handler to add to this event
     */
    operator fun plusAssign(handler: () -> Unit)

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    fun removeHandler(handler: (event: TEvent) -> Unit)

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    fun removeHandler(handler: () -> Unit)

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    operator fun minusAssign(handler: (event: TEvent) -> Unit)

    /**
     * Remove an event handler from this listener.
     *
     * @param handler the event handler to remove from this listener
     */
    operator fun minusAssign(handler: () -> Unit)

    /**
     * Implementation of [Listener].
     */
    class Impl<TEvent> : Listener<TEvent> {

        /**
         * Event handlers with an event parameter.
         */
        private val handlersParam = mutableListOf<(event: TEvent) -> Unit>()

        /**
         * Event handlers without an event parameter.
         */
        private val handlersNoParam = mutableListOf<() -> Unit>()

        override fun notifyHandlers(event: TEvent) {
            handlersParam.forEach { it(event) }
            handlersNoParam.forEach { it() }
        }

        override fun addHandler(handler: (event: TEvent) -> Unit) {
            handlersParam += handler
        }

        override fun addHandler(handler: () -> Unit) {
            handlersNoParam += handler
        }

        override operator fun plusAssign(handler: (event: TEvent) -> Unit) {
            addHandler(handler)
        }

        override operator fun plusAssign(handler: () -> Unit) {
            addHandler(handler)
        }

        override fun removeHandler(handler: (event: TEvent) -> Unit) {
            handlersParam -= handler
        }

        override fun removeHandler(handler: () -> Unit) {
            handlersNoParam -= handler
        }

        override operator fun minusAssign(handler: (event: TEvent) -> Unit) {
            removeHandler(handler)
        }

        override operator fun minusAssign(handler: () -> Unit) {
            removeHandler(handler)
        }
    }
}
