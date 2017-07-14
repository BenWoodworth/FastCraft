package net.benwoodworth.fastcraft.dependencies.text

/**
 * Dagger module for text dependencies.
 */
interface ModuleText {

    fun textBuilder(): TextBuilder

    fun textColorRegistry(): TextColorRegistry
}
