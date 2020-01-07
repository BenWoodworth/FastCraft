package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.crafting.model.FastCraftRecipe
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class RecipeButtonView(
    private val button: FcGuiButton,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors
) {
    var fastCraftRecipe: FastCraftRecipe? = null

    var eventListener: EventListener = EventListener.Default

    init {
        button.eventListener = ButtonEventListener()
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

    interface EventListener {
        object Default : EventListener

        fun onCraft(recipe: FastCraftRecipe, dropResults: Boolean) {}
    }

    private companion object {
        val CLICK_CRAFT = FcGuiClick.Primary()
        val CLICK_CRAFT_DROP = FcGuiClick.Drop()
    }

    private inner class ButtonEventListener : FcGuiButton.EventListener {
        override fun onClick(gui: FcGui<*>, button: FcGuiButton, click: FcGuiClick) {
            val recipe = fastCraftRecipe
            if (recipe != null) {
                when (click) {
                    CLICK_CRAFT -> eventListener.onCraft(recipe, false)
                    CLICK_CRAFT_DROP -> eventListener.onCraft(recipe, true)
                }
            }
        }
    }
}
