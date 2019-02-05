package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEvent

interface FcGuiCloseEvent : FcEvent {

    val gui: FcGui<*>
}
