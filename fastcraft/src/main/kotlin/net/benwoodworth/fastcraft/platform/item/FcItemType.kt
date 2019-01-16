package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable

interface FcItemType {

    val name: FcTranslatable

    val description: FcTranslatable

    val maxAmount: Int
}
