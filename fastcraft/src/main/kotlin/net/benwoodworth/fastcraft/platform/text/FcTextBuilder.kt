package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Builder
import net.benwoodworth.fastcraft.util.Extensible

interface FcTextBuilder : Extensible, Builder<FcText> {

    var color: FcTextColor?

    var bold: Boolean?

    var italic: Boolean?

    var underline: Boolean?

    var strikethrough: Boolean?

    var obfuscate: Boolean?

    var extra: MutableList<FcText>
}
