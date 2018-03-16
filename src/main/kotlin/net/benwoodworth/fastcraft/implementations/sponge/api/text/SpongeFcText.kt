package net.benwoodworth.fastcraft.implementations.sponge.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextAction
import net.benwoodworth.fastcraft.dependencies.api.text.FcTextColor
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.text.Text
import javax.inject.Inject

class SpongeFcText(
        override val base: Text
) : Adapter<Text>(), FcText {

    class Builder @Inject constructor(
    ) : FcText.Builder {

        override fun setText(text: String) = Specific(Text.builder(text))

        class Specific(
                override val base: Text.Builder
        ) : Adapter<Text.Builder>(), FcText.Builder.Specific {

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

            override fun setObfuscated(obfuscated: Boolean) = also {
                base.style.obfuscated(obfuscated)
            }

            override fun setShiftClickAction(action: FcTextAction.ShiftClick) = also {
                base.onShiftClick((action as SpongeFcTextAction.ShiftClick<*>).base)
            }

            override fun setClickAction(action: FcTextAction.Click) = also {
                base.onClick((action as SpongeFcTextAction.Click<*>).base)
            }

            override fun setHoverAction(action: FcTextAction.Hover) = also {
                base.onHover((action as SpongeFcTextAction.Hover<*>).base)
            }

            override fun addExtra(text: FcText) = also {
                base.append((text as SpongeFcText).base)
            }
        }
    }
}