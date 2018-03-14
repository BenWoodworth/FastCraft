package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextAction
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeFcItem
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.action.TextAction
import org.spongepowered.api.text.action.TextActions
import java.net.URL
import java.util.*
import javax.inject.Inject

sealed class SpongeFcTextAction : Adapter<TextAction<*>>(), FcTextAction {

    class Hover(
            override val base: TextAction<*>
    ) : SpongeFcTextAction(), FcTextAction.Hover {

        class Factory @Inject constructor(
        ) : FcTextAction.Hover.Factory {

            override fun showText(text: FcText): Hover {
                return Hover(TextActions.showText((text as SpongeFcText).base))
            }

            override fun showItem(item: FcItem): Hover {
                return Hover(TextActions.showItem((item as SpongeFcItem).snapshot))
            }

            override fun showEntity(id: UUID, name: String): Hover {
                return Hover(TextActions.showEntity(id, name))
            }
        }
    }

    class Click(
            override val base: TextAction<*>
    ) : SpongeFcTextAction(), FcTextAction.Click {

        interface Factory : FcTextAction.Click.Factory {
            override fun openUrl(url: URL): Click {
                return Click(TextActions.openUrl(url))
            }

            override fun runCommand(command: String): Click {
                return Click(TextActions.runCommand(command))
            }

            override fun suggestCommand(command: String): Click {
                return Click(TextActions.suggestCommand(command))
            }

            override fun changePage(page: Int): Click {
                return Click(TextActions.changePage(page))
            }
        }
    }

    class ShiftClick(
            override val base: TextAction<*>
    ) : SpongeFcTextAction(), FcTextAction.ShiftClick {

        interface Factory : FcTextAction.ShiftClick.Factory {
            override fun insertText(text: String): ShiftClick {
                return ShiftClick(TextActions.insertText(text))
            }
        }
    }
}