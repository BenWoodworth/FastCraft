package net.benwoodworth.fastcraft.api.mvp

/**
 * The "view" aspect of the [MVP architectural pattern](http://bit.ly/WikiMVP).
 *
 * Should only contains logic for setting up the UI and event listeners.
 * All other UI logic should be kept in the [Presenter].
 *
 * Responsibilities:
 * - Setup a user interface that can be used by a [Presenter].
 * - Create event handlers for user input that a [Presenter] can listen to.
 * - Provide read and write access to user interface elements.
 */
interface View
