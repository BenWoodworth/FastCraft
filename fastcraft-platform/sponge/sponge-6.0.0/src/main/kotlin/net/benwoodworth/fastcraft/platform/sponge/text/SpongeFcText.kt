package net.benwoodworth.fastcraft.platform.sponge.text

import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.api.text.FcTextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.Text
import javax.inject.Inject

class SpongeFcText(
    override val base: Text
) : Adapter<Text>(), FcText {

    class Factory : FcText.Factory {

        override fun buildText(text: String) = Builder(Text.builder(text))
    }

    class Builder @Inject constructor(
        override val base: Text.Builder
    ) : Adapter<Text.Builder>(), FcText.Builder {

        override fun build() = SpongeFcText(base.build())

        override fun setColor(color: FcTextColor) = also {
            base.color((color as SpongeFcTextColor).base)
        }

        override fun setBold(bold: Boolean) = also {
            base.style.bold(bold)
        }

        override fun setItalic(italic: Boolean) = also {
            base.style.italic(italic)
        }

        override fun setUnderlined(underlined: Boolean) = also {
            base.style.underline(underlined)
        }

        override fun setStrikeThrough(strikeThrough: Boolean) = also {
            base.style.strikethrough(strikeThrough)
        }

        override fun setObfuscated(obfuscate: Boolean) = also {
            base.style.obfuscate(obfuscate)
        }

        //override fun setShiftClickAction(action: FcTextAction.ShiftClick) = also {
        //    base.onShiftClick((action as SpongeFcTextAction.ShiftClick<*>).base)
        //}

        //override fun setClickAction(action: FcTextAction.Click) = also {
        //    base.onClick((action as SpongeFcTextAction.Click<*>).base)
        //}

        //override fun setHoverAction(action: FcTextAction.Hover) = also {
        //    base.onHover((action as SpongeFcTextAction.Hover<*>).base)
        //}

        override fun addExtra(text: FcText) = also {
            base.append((text as SpongeFcText).base)
        }
    }
}
