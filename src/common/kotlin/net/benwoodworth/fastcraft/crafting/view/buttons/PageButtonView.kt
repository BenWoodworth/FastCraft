package net.benwoodworth.fastcraft.crafting.view.buttons

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory

@AutoFactory
class PageButtonView(
    private val button: FcGuiButton,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val textColors: FcTextColors
) {
    var page: Int = 1
    var pageCount: Int = 1

    lateinit var onPageNext: () -> Unit
    lateinit var onPagePrevious: () -> Unit
    lateinit var onPageFirst: () -> Unit

    init {
        button.apply {
            onClick = { event ->
                when {
                    event.isPrimaryClick -> when {
                        event.isShiftClick -> onPageFirst()
                        else -> onPageNext()
                    }
                    event.isSecondaryClick -> onPagePrevious()
                }
            }

            itemType = itemTypes.ironSword

            description = listOf(
                textFactory.createFcText("Left click: next page", color = textColors.aqua),
                textFactory.createFcText("Right click: previous page", color = textColors.aqua),
                textFactory.createFcText("Shift click: first page", color = textColors.aqua)
            )

            hideItemDetails()
        }

        update()
    }

    fun update() {
        button.apply {
            text = textFactory.createFcText(
                text = "Page ${page}/${pageCount}",
                color = textColors.green
            )
        }
    }
}
