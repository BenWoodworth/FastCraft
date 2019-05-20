package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import kotlin.math.ceil
import kotlin.math.max

class CraftingGuiModel(
    private val player: FcPlayer,
    private val itemFactory: FcItemFactory,
    private val itemTypes: FcItemTypes,
    private val textFactory: FcTextFactory
) {

    var recipes: List<FcItem> = emptyList()

    fun refreshRecipes() {
        with(itemFactory) {
            with(textFactory) {
                recipes = (1..100).map {
                    createFcItem(
                        type = itemTypes.netherStar,
                        amount = it,
                        displayName = createFcText(
                            text = it.toString()
                        )
                    )
                }
            }
        }

        pages.first()
    }

    val multiplier = Multiplier()

    inner class Multiplier {
        private val range = 1..64
        private val steps = listOf(1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64)

        var value: Int = 1
            private set(value) {
                field = value.coerceIn(range)
            }

        fun minimize() {
            value = range.start
        }

        fun maximize() {
            value = range.endInclusive
        }

        fun increase() {
            value++
        }

        fun decrease() {
            value--
        }

        fun increment() {
            value = steps
                .firstOrNull { it > value }
                ?: range.endInclusive
        }

        fun decrement() {
            value = steps
                .firstOrNull { it < value }
                ?: range.start
        }
    }

    val pages = Pages()

    inner class Pages {
        var pageSize: Int = 0

        var current: Int = 1
            private set(value) {
                field = value.coerceIn(1..count)
            }

        val count: Int
            get() = max(1, ceil(recipes.size / pageSize.toDouble()).toInt())

        val pageRecipes: List<FcItem?>
            get() {
                val startIndex = (current - 1) * pageSize
                return List(pageSize) { i ->
                    recipes.getOrNull(i)
                }
            }

        fun previous() {
            current--
        }

        fun next() {
            current++
        }

        fun first() {
            current = 1
        }

        fun last() {
            current = count
        }
    }
}