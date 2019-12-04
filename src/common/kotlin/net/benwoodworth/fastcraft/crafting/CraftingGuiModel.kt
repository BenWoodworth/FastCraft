package net.benwoodworth.fastcraft.crafting

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.max

@AutoFactory
class CraftingGuiModel(
    private val player: FcPlayer,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val recipeService: FcRecipeService
) {

    var recipes: List<FcItem> = emptyList()

    fun refreshRecipes() {
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