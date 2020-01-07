package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class RecipeButtonView(
    private val button: FcGuiButton,
    @Provided private val textFactory: FcTextFactory
) {
    var fastCraftRecipe: FastCraftRecipe? = null

    var listener: Listener = Listener.Default

    init {
        button.listener = ButtonListener()
    }

    fun update() {
        button.apply {
            button.clear()

            fastCraftRecipe?.preparedRecipe?.let { recipe ->
                val previewItem = recipe.resultsPreview.first()
                copyItem(previewItem)

                val newDescription = mutableListOf(
                    textFactory.createFcText("Ingredients:")
                )

                if (previewItem.lore.any()) {
                    newDescription += textFactory.createFcText()
                    newDescription += previewItem.lore
                }

                description = newDescription
            }
        }
    }

    interface Listener {
        object Default : Listener

        fun onCraft(recipe: FastCraftRecipe, dropResults: Boolean) {}
    }

    private companion object {
        val CLICK_CRAFT = FcGuiClick.Primary()
        val CLICK_CRAFT_DROP = FcGuiClick.Drop()
    }

    private inner class ButtonListener : FcGuiButton.Listener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val recipe = fastCraftRecipe
            if (recipe != null) {
                when (click) {
                    CLICK_CRAFT -> listener.onCraft(recipe, false)
                    CLICK_CRAFT_DROP -> listener.onCraft(recipe, true)
                }
            }
        }
    }
}
