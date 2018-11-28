package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.util.Extensible

interface FcItemFactory : Extensible {

    fun buildItem(copy: FcItem? = null, build: FcItemBuilder.() -> Unit): FcItem
}