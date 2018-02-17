package net.benwoodworth.fastcraft.dependencies.api.mvp

/**
 * The "view" aspect of the [MVP architectural pattern](http://bit.ly/WikiMVP).
 *
 * Should only contains logic for setting up the UI and event listeners.
 * All other UI logic should be kept in the [MvpPresenter].
 *
 * Responsibilities:
 * - Setup a user interface that can be used by a [MvpPresenter].
 * - Create event handlers for user input that a [MvpPresenter] can listen to.
 * - Provide read and write access to user interface elements.
 */
interface MvpView
