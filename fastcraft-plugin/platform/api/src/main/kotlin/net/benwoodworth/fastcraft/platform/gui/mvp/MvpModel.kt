package net.benwoodworth.fastcraft.platform.gui.mvp

import net.benwoodworth.fastcraft.platform.event.FcListener

/**
 * The "model" aspect of the [MVP architectural pattern](http://bit.ly/WikiMVP).
 *
 * Responsibilities:
 * - Receive method calls from the [MvpPresenter].
 * - Make appropriate changes to data from method calls.
 * - Call the [updated] method after making changes to notify the [MvpPresenter].
 * - Provide read-only access to data for the [MvpPresenter] to use.
 */
abstract class MvpModel {

    /**
     * A listener to notify [MvpPresenter]s when this model changes.
     */
    val changeListener = FcListener<MvpModel>()

    /**
     * Notifies observing [MvpPresenter]s that this model has changed.
     *
     * Should be called whenever changes are made to the [MvpModel].
     */
    protected fun updated() {
        changeListener.notifyHandlers(this)
    }
}
