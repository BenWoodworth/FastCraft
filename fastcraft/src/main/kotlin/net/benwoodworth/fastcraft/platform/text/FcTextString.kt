package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Extensible

interface FcTextString : Extensible, FcText {

    val text: String
}
