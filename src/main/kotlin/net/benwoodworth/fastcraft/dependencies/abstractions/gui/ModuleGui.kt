package net.benwoodworth.fastcraft.dependencies.abstractions.gui

/**
 * Dagger module for GUI dependencies.
 */
interface ModuleGui {

    fun guiBuilder(): Gui.Builder
}
