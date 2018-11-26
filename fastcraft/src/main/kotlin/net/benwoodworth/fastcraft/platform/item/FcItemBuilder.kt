package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Builder
import net.benwoodworth.fastcraft.util.Extensible

interface FcItemBuilder : Extensible, Builder<FcItem> {

    var type: FcItemType

    var amount: Int

    var displayName: FcText?

    var lore: MutableList<FcText>
}
