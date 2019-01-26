package net.benwoodworth.fastcraft.platform.item

interface FcItem {

    val type: FcItemType

    val amount: Int

    val displayName: String?

    val lore: List<String>?
}
