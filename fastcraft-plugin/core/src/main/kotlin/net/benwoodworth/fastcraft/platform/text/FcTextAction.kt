//package net.benwoodworth.fastcraft.platform.text
//
//import net.benwoodworth.fastcraft.platform.item.FcItem
//import java.net.URL
//import java.util.*
//
//interface FcTextAction {
//
//    interface Factory {
//        fun showText(text: FcText): Hover.ShowText
//        fun showItem(item: FcItem): Hover.ShowItem
//        fun showEntity(id: UUID, name: String): Hover.ShowEntity
//
//        fun openUrl(url: URL): Click.OpenUrl
//        fun runCommand(command: String): Click.RunCommand
//        fun suggestCommand(command: String): Click.SuggestCommand
//        fun changePage(page: Int): Click.ChangePage
//
//        fun insertText(text: String): ShiftClick.InsertText
//    }
//
//    interface Hover : FcTextAction {
//        interface ShowText : Hover
//        interface ShowItem : Hover
//        interface ShowEntity : Hover
//    }
//
//    interface Click : FcTextAction {
//        interface OpenUrl : Click
//        interface RunCommand : Click
//        interface SuggestCommand : Click
//        interface ChangePage : Click
//    }
//
//    interface ShiftClick : FcTextAction {
//        interface InsertText : ShiftClick
//    }
//}