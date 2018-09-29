//package net.benwoodworth.fastcraft.platform.sponge.text
//
//import net.benwoodworth.fastcraft.platform.api.item.FcItem
//import net.benwoodworth.fastcraft.platform.api.text.FcText
//import net.benwoodworth.fastcraft.platform.api.text.FcTextAction
//import net.benwoodworth.fastcraft.platform.sponge.item.SpongeFcItem
//import net.benwoodworth.fastcraft.util.Adapter
//import org.spongepowered.api.text.action.*
//import java.net.URL
//import java.util.*
//import javax.inject.Inject
//
//sealed class SpongeFcTextAction<out T : TextAction<*>> : Adapter<T>(), FcTextAction {
//
//    class Factory @Inject constructor(
//    ) : FcTextAction.Factory {
//
//        override fun showText(text: FcText) = Hover.ShowText(TextActions.showText((text as SpongeFcText).base))
//        override fun showItem(item: FcItem) = Hover.ShowItem(TextActions.showItem((item as SpongeFcItem).snapshot))
//        override fun showEntity(id: UUID, name: String) = Hover.ShowEntity(TextActions.showEntity(id, name))
//
//        override fun openUrl(url: URL) = Click.OpenUrl(TextActions.openUrl(url))
//        override fun runCommand(command: String) = Click.RunCommand(TextActions.runCommand(command))
//        override fun suggestCommand(command: String) = Click.SuggestCommand(TextActions.suggestCommand(command))
//        override fun changePage(page: Int) = Click.ChangePage(TextActions.changePage(page))
//
//        override fun insertText(text: String) = ShiftClick.InsertText(TextActions.insertText(text))
//    }
//
//    abstract class Hover<out T : HoverAction<*>> : SpongeFcTextAction<T>(), FcTextAction.Hover {
//
//        class ShowText(
//                override val base: HoverAction.ShowText
//        ) : Hover<HoverAction.ShowText>(), FcTextAction.Hover.ShowText
//
//        class ShowItem(
//                override val base: HoverAction.ShowItem
//        ) : Hover<HoverAction.ShowItem>(), FcTextAction.Hover.ShowItem
//
//        class ShowEntity(
//                override val base: HoverAction.ShowEntity
//        ) : Hover<HoverAction.ShowEntity>(), FcTextAction.Hover.ShowEntity
//    }
//
//    abstract class Click<out T : ClickAction<*>> : SpongeFcTextAction<T>(), FcTextAction.Click {
//
//        class OpenUrl(
//                override val base: ClickAction.OpenUrl
//        ) : Click<ClickAction.OpenUrl>(), FcTextAction.Click.OpenUrl
//
//        class RunCommand(
//                override val base: ClickAction.RunCommand
//        ) : Click<ClickAction.RunCommand>(), FcTextAction.Click.RunCommand
//
//        class SuggestCommand(
//                override val base: ClickAction.SuggestCommand
//        ) : Click<ClickAction.SuggestCommand>(), FcTextAction.Click.SuggestCommand
//
//        class ChangePage(
//                override val base: ClickAction.ChangePage
//        ) : Click<ClickAction.ChangePage>(), FcTextAction.Click.ChangePage
//    }
//
//    abstract class ShiftClick<out T : ShiftClickAction<*>> : SpongeFcTextAction<T>(), FcTextAction.ShiftClick {
//
//        class InsertText(
//                override val base: ShiftClickAction.InsertText
//        ) : ShiftClick<ShiftClickAction.InsertText>(), FcTextAction.ShiftClick.InsertText
//    }
//}
