package net.benwoodworth.fastcraft.core.dependencies.text

/**
 * Dagger module for text dependencies.
 */
interface ModuleText {

    fun textBuilder(): TextBuilder

    fun textColorRegistry(): TextColorRegistry
}
