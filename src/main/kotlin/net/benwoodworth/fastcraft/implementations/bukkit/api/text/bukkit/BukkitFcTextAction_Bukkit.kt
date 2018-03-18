//package net.benwoodworth.fastcraft.implementations.bukkit.api.text.bukkit
//
//import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
//import net.benwoodworth.fastcraft.dependencies.api.text.FcText
//import net.benwoodworth.fastcraft.dependencies.api.text.FcTextAction
//import java.net.URL
//import java.util.*
//import javax.inject.Inject
//
//sealed class BukkitFcTextAction_Bukkit : FcTextAction {
//
//    abstract val action: String
//
//    class Factory @Inject constructor(
//    ) : FcTextAction.Factory {
//
//        override fun showText(text: FcText) = Hover.ShowText(text)
//        override fun showItem(item: FcItem) = Hover.ShowItem(item)
//        override fun showEntity(id: UUID, name: String) = Hover.ShowEntity(id, name)
//
//        override fun openUrl(url: URL) = Click.OpenUrl(url)
//        override fun runCommand(command: String) = Click.RunCommand(command)
//        override fun suggestCommand(command: String) = Click.SuggestCommand(command)
//        override fun changePage(page: Int) = Click.ChangePage(page)
//
//        override fun insertText(text: String) = ShiftClick.InsertText(text)
//    }
//
//    sealed class Hover : BukkitFcTextAction_Bukkit() {
//        class ShowText(val text: FcText) : Hover(), FcTextAction.Hover.ShowText {
//            override val action = "show_text"
//        }
//
//        class ShowItem(val item: FcItem) : Hover(), FcTextAction.Hover.ShowItem {
//            override val action = "show_item"
//        }
//
//        class ShowEntity(val entityUuid: UUID, entityName: String) : Hover(), FcTextAction.Hover.ShowEntity {
//            override val action = "show_entity"
//        }
//    }
//
//    sealed class Click : BukkitFcTextAction_Bukkit() {
//        class OpenUrl(val url: URL) : Click(), FcTextAction.Click.OpenUrl {
//            override val action = "open_url"
//        }
//
//        class RunCommand(val command: String) : Click(), FcTextAction.Click.RunCommand {
//            override val action = "run_command"
//        }
//
//        class SuggestCommand(val command: String) : Click(), FcTextAction.Click.SuggestCommand {
//            override val action = "suggest_command"
//        }
//
//        class ChangePage(val page: Int) : Click(), FcTextAction.Click.ChangePage {
//            override val action = "change_page"
//        }
//    }
//
//    sealed class ShiftClick : BukkitFcTextAction_Bukkit() {
//        class InsertText(val text: String) : ShiftClick(), FcTextAction.ShiftClick.InsertText {
//            override val action = "insert_text"
//        }
//    }
//}