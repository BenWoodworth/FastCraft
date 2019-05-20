package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItem {

    val type: FcItemType

    val amount: Int

    val displayName: FcText?

    val lore: List<FcText>
}
