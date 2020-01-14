package net.benwoodworth.fastcraft.crafting.presenter

import net.benwoodworth.fastcraft.crafting.model.FastCraftGuiModel
import net.benwoodworth.fastcraft.crafting.view.FastCraftGuiView
import net.benwoodworth.fastcraft.crafting.view.buttons.PageButtonView

class FastCraftGuiPresenter(
    private val model: FastCraftGuiModel,
    private val view: FastCraftGuiView
) {
    private var recipesPage = 1
    private var recipesPageCount = 5 // TODO Change to 1

    init {
        view.pageButton.listener = PageButtonListener()

        updatePageButton()
    }

    fun openGui() {
        view.gui.open()
    }

    private fun updatePageButton() {
        view.pageButton.apply {
            page = recipesPage
            pageCount = recipesPageCount
            update()
        }
    }

    private inner class PageButtonListener : PageButtonView.Listener {
        override fun onPageNext() {
            recipesPage = minOf(recipesPage + 1, recipesPageCount)
            updatePageButton()
        }

        override fun onPagePrevious() {
            recipesPage = maxOf(1, recipesPage - 1)
            updatePageButton()
        }

        override fun onPageFirst() {
            recipesPage = 1
            updatePageButton()
        }
    }
}
