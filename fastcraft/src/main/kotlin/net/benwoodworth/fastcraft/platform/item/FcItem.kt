package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Extensible

interface FcItem : Extensible {

    val type: FcItemType

    val amount: Int

    val displayName: FcText

    val lore: List<FcText>

    val maxAmount: Int
}
