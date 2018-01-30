package net.benwoodworth.fastcraft.core.api.mvp

/**
 * The "presenter" aspect of the [MVP architectural pattern](http://bit.ly/WikiMVP).
 *
 * Acts as a mediator between the [View] and the [Model].
 * Responds to user input from the [View], and makes the
 * appropriate method calls to the [Model]. Listens to the
 * [Model] for changes, and translates the data to the [View].
 *
 * Responsibilities:
 * - Registers listeners in the [View] for user interaction.
 * - Translates user interaction from the [View] to method calls in the [Model].
 * - Updates the [View] from [updateView] after the model changes.
 * - Translates data from the [Model] to info in the [View].
 *
 * @param view the [View] being maintained
 * @param model the [Model] being presented
 * @param TView the type of [View] being maintained
 * @param TModel the type of [Model] being presented
 */
abstract class Presenter<out TView : View, out TModel : Model>(

        /**
         * The [View] being maintained by this [Presenter].
         */
        protected val view: TView,

        /**
         * The [Model] being presented by this [Presenter].
         */
        protected val model: TModel
) {

    init {
        // Subscribe to model changes
        model.changeListener += ::updateView
    }

    /**
     * Updates the view to match the model.
     *
     * Called automatically when [Model.updated] is invoked.
     */
    protected abstract fun updateView()
}
