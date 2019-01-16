package net.benwoodworth.fastcraft.platform.item

interface FcItemFactory {

    fun buildItem(copy: FcItem? = null, build: FcItemBuilder.() -> Unit): FcItem
}