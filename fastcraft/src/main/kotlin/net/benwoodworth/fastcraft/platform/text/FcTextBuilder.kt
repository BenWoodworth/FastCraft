package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.util.Builder

interface FcTextBuilder : Builder<FcText> {

    var text: String?

    var translate: FcTranslatable?

    var color: FcTextColor?

    var bold: Boolean?

    var italic: Boolean?

    var underline: Boolean?

    var strikethrough: Boolean?

    var obfuscate: Boolean?

    var extra: MutableList<FcText>
}
