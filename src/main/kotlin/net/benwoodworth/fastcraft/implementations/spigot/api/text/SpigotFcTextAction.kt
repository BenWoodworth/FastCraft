package net.benwoodworth.fastcraft.implementations.spigot.api.text

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextAction
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import java.net.URL
import java.util.*

sealed class SpigotFcTextAction : FcTextAction {

    class Factory {
        fun showText(text: FcText): Hover.ShowText
        fun showItem(item: FcItem): Hover.ShowItem
        fun showEntity(id: UUID, name: String): Hover.ShowEntity

        fun openUrl(url: URL): Click.OpenUrl
        fun runCommand(command: String): Click.RunCommand
        fun suggestCommand(command: String): Click.SuggestCommand
        fun changePage(page: Int): Click.ChangePage

        fun insertText(text: String): ShiftClick.InsertText
    }

    sealed class Hover(
            val event: HoverEvent
    ) : SpigotFcTextAction() {

        class ShowText(text) : Hover(
                HoverEvent.Action.SHOW_TEXT,
                TextCompo
        )

        class ShowItem(text) : Hover(HoverEvent(
                HoverEvent.Action.SHOW_ITEM,
                ComponentBuilder()
        ))

        class ShowEntity(text) : Hover(
                HoverEvent.Action.SHOW_ENTITY,

                )
    }

    sealed class Click(
            val event: ClickEvent
    ) : SpigotFcTextAction() {

        class OpenUrl : Click
        class RunCommand : Click
        class SuggestCommand : Click
        class ChangePage : Click
    }

    sealed class ShiftClick : SpigotFcTextAction() {
        class InsertText : ShiftClick
    }
}