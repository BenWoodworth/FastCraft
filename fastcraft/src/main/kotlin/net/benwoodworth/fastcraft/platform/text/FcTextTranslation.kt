package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Extensible

interface FcTextTranslation : Extensible, FcText {

    val translationKey: String
}
