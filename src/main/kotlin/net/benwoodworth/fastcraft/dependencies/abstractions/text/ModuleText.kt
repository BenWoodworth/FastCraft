package net.benwoodworth.fastcraft.dependencies.abstractions.text

/**
 * Dagger module for text dependencies.
 */
interface ModuleText {

    fun textBuilder(): Text.Builder

    fun textColorRegistry(): TextColorRegistry
}
