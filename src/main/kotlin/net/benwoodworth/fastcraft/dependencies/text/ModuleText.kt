package net.benwoodworth.fastcraft.dependencies.text

/**
 * Dagger module for text dependencies.
 */
interface ModuleText {

    fun textBuilder(): Text.Builder

    fun textColorRegistry(): TextColorRegistry
}
