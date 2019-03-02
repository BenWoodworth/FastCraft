package net.benwoodworth.fastcraft.crafting

import net.benwoodworth.fastcraft.platform.server.FcPlayer

class CraftingGuiModel(
    private val player: FcPlayer
) {
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

    val pages = Page()

    inner class Page {
        var current: Int = 1
            private set(value) {
                field = value.coerceIn(1..total)
            }

        val total: Int = 1

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
            current = total
        }
    }
}