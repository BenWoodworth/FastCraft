package net.benwoodworth.fastcraft.platform.gui

interface FcGuiClickEvent {
    val gui: FcGui<*>
    val button: FcGuiButton
    val click: FcGuiClick
}
