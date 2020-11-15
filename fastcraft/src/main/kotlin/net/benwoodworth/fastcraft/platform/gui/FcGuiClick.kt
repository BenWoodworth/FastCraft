package net.benwoodworth.fastcraft.platform.gui

sealed class FcGuiClick {
    abstract val modifiers: Set<FcGuiClickModifier>

    data class Primary(
        override val modifiers: Set<FcGuiClickModifier> = emptySet(),
    ) : FcGuiClick() {
        constructor(vararg modifiers: FcGuiClickModifier) : this(setOf(*modifiers))
    }

    data class Secondary(
        override val modifiers: Set<FcGuiClickModifier> = emptySet(),
    ) : FcGuiClick() {
        constructor(vararg modifiers: FcGuiClickModifier) : this(setOf(*modifiers))
    }

    data class Middle(
        override val modifiers: Set<FcGuiClickModifier> = emptySet(),
    ) : FcGuiClick() {
        constructor(vararg modifiers: FcGuiClickModifier) : this(setOf(*modifiers))
    }

    data class Number(
        val number: Int,
        override val modifiers: Set<FcGuiClickModifier> = emptySet(),
    ) : FcGuiClick() {
        constructor(number: Int, vararg modifiers: FcGuiClickModifier) : this(number, setOf(*modifiers))
    }

    data class Drop(
        override val modifiers: Set<FcGuiClickModifier> = emptySet(),
    ) : FcGuiClick() {
        constructor(vararg modifiers: FcGuiClickModifier) : this(setOf(*modifiers))
    }
}
