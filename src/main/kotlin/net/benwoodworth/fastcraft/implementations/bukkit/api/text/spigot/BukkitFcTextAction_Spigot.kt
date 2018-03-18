//package net.benwoodworth.fastcraft.implementations.bukkit.api.text.spigot
//
//import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
//import net.benwoodworth.fastcraft.dependencies.api.text.FcText
//import net.benwoodworth.fastcraft.dependencies.api.text.FcTextAction
//import net.benwoodworth.fastcraft.util.Adapter
//import net.md_5.bungee.api.chat.ClickEvent
//import net.md_5.bungee.api.chat.ComponentBuilder
//import net.md_5.bungee.api.chat.HoverEvent
//import java.net.URL
//import java.util.*
//
//sealed class BukkitFcTextAction_Spigot<out TEvent> : Adapter<TEvent>(), FcTextAction {
//
//    class Factory : FcTextAction.Factory {
//        override fun showText(text: List<FcText>) = Hover.ShowText
//        override fun showItem(item: FcItem) = Hover.ShowItem
//        override fun showEntity(id: UUID, name: String) = Hover.ShowEntity
//
//        override fun openUrl(url: URL) = Click.OpenUrl
//        override fun runCommand(command: String) = Click.RunCommand
//        override fun suggestCommand(command: String) = Click.SuggestCommand
//        override fun changePage(page: Int) = Click.ChangePage
//
//        override fun insertText(text: String) = ShiftClick.InsertText
//    }
//
//    sealed class Hover(
//            override val base: HoverEvent
//    ) : BukkitFcTextAction_Spigot<HoverEvent>() {
//
//        class ShowText(
//                val text: List<FcText>
//        ) : Hover(HoverEvent(
//                HoverEvent.Action.SHOW_TEXT,
//                text.map { (it as BukkitFcText_Spigot).base }.toTypedArray()
//        ))
//
//        class ShowItem(item: FcItem) : Hover(HoverEvent(
//                HoverEvent.Action.SHOW_ITEM,
//
//        ))
//
//        class ShowEntity(text) : Hover(
//                HoverEvent.Action.SHOW_ENTITY,
//
//                )
//    }
//
//    sealed class Click(
//            val event: ClickEvent
//    ) : BukkitFcTextAction_Spigot() {
//
//        class OpenUrl : Click
//        class RunCommand : Click
//        class SuggestCommand : Click
//        class ChangePage : Click
//    }
//
//    sealed class ShiftClick : BukkitFcTextAction_Spigot() {
//        class InsertText : ShiftClick
//    }
//}