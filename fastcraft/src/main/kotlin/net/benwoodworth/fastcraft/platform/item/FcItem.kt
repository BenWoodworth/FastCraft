package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcLegacyText

interface FcItem {

    val type: FcItemType

    val amount: Int

    val displayName: FcLegacyText?

    val lore: List<FcLegacyText>?
}
