package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Builder

interface FcItemBuilder : Builder<FcItem> {

    var type: FcItemType

    var amount: Int

    var displayName: FcText?

    var lore: MutableList<FcText>
}
