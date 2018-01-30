package net.benwoodworth.fastcraft.api.mvp

import net.benwoodworth.fastcraft.api.Listener

/**
 * The "model" aspect of the [MVP architectural pattern](http://bit.ly/WikiMVP).
 *
 * Responsibilities:
 * - Receive method calls from the [Presenter].
 * - Make appropriate changes to data from method calls.
 * - Call the [updated] method after making changes to notify the [Presenter].
 * - Provide read-only access to data for the [Presenter] to use.
 */
abstract class Model {

    /**
     * A listener to notify [Presenter]s when this model changes.
     */
    val changeListener = Listener<Model>()

    /**
     * Notifies observing [Presenter]s that this model has changed.
     *
     * Should be called whenever changes are made to the [Model].
     */
    protected fun updated() {
        changeListener.notifyHandlers(this)
    }
}
