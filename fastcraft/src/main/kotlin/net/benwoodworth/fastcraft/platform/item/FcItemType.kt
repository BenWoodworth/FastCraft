package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.util.Extensible

interface FcItemType : Extensible {

    val name: FcTranslatable

    val description: FcTranslatable

    val maxAmount: Int
}
