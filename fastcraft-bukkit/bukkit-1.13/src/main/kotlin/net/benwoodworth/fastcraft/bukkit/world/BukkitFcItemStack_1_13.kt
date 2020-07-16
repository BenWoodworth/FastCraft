package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcItemStack_1_13 {
    @Singleton
    class TypeClass @Inject constructor(
        private val items: FcItem.Factory,
        textFactory: FcText.Factory,
        private val fcItemTypeClass: FcItem.TypeClass,
        fcItemStackFactory: FcItemStack.Factory,
    ) : BukkitFcItemStack_1_7.TypeClass(
        items = items,
        textFactory = textFactory,
        fcItemTypeClass = fcItemTypeClass,
        fcItemStackFactory = fcItemStackFactory,
    ) {
        override var FcItemStack.type: FcItem
            get() = items.fromMaterial(itemStack.type)
            set(value) {
                itemStack.type = fcItemTypeClass.bukkit.run { value.material }
            }
    }
}
