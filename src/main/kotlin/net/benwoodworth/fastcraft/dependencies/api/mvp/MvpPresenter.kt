package net.benwoodworth.fastcraft.dependencies.api.mvp

/**
 * The "presenter" aspect of the [MVP architectural pattern](http://bit.ly/WikiMVP).
 *
 * Acts as a mediator between the [MvpView] and the [MvpModel].
 * Responds to user input from the [MvpView], and makes the
 * appropriate method calls to the [MvpModel]. Listens to the
 * [MvpModel] for changes, and translates the data to the [MvpView].
 *
 * Responsibilities:
 * - Registers listeners in the [MvpView] for user interaction.
 * - Translates user interaction from the [MvpView] to method calls in the [MvpModel].
 * - Updates the [MvpView] from [updateView] after the model changes.
 * - Translates data from the [MvpModel] to info in the [MvpView].
 *
 * @param view the [MvpView] being maintained
 * @param model the [MvpModel] being presented
 * @param TView the type of [MvpView] being maintained
 * @param TModel the type of [MvpModel] being presented
 */
abstract class MvpPresenter<out TView : MvpView, out TModel : MvpModel>(

        /**
         * The [MvpView] being maintained by this [MvpPresenter].
         */
        protected val view: TView,

        /**
         * The [MvpModel] being presented by this [MvpPresenter].
         */
        protected val model: TModel
) {

    init {
        // Subscribe to model changes
        model.changeListener += ::updateView
    }

    /**
     * Updates the [view] to match the [model].
     *
     * Called automatically when [MvpModel.updated] is invoked.
     */
    protected abstract fun updateView()
}
