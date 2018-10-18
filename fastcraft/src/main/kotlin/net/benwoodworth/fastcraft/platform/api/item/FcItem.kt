package net.benwoodworth.fastcraft.platform.api.item

import net.benwoodworth.fastcraft.platform.api.text.FcText

/**
 * A Minecraft item.
 */
interface FcItem {

    val type: FcItemType

    val amount: Int

    val name: FcText

    val displayName: FcText?

    val lore: List<FcText>?

    val maxStackSize: Int

    val durability: Int
}
