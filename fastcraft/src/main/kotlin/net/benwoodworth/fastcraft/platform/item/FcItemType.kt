package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Extensible

interface FcItemType : Extensible {

    val id: String

    val name: FcText
}
