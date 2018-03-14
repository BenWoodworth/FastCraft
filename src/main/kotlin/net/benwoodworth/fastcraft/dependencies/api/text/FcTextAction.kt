package net.benwoodworth.fastcraft.dependencies.api.text

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import java.net.URL
import java.util.*

interface FcTextAction {

    interface Hover : FcTextAction {
        interface Factory {
            fun showText(text: FcText): Hover
            fun showItem(item: FcItem): Hover
            fun showEntity(id: UUID, name: String): Hover
        }
    }

    interface Click : FcTextAction {
        interface Factory {
            fun openUrl(url: URL): Click
            fun runCommand(command: String): Click
            fun suggestCommand(command: String): Click
            fun changePage(page: Int): Click
        }
    }

    interface ShiftClick : FcTextAction {
        interface Factory {
            fun insertText(text: String): ShiftClick
        }
    }
}