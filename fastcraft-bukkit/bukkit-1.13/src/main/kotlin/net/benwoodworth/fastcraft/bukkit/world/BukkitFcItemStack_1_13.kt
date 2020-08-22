package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcItemStack_1_13 {
    @Singleton
    class Operations @Inject constructor(
        private val fcItemFactory: FcItem.Factory,
        fcTextFactory: FcText.Factory,
        private val fcItemOperations: FcItem.Operations,
        fcItemStackFactory: FcItemStack.Factory,
    ) : BukkitFcItemStack_1_7.Operations(
        fcItemFactory = fcItemFactory,
        fcTextFactory = fcTextFactory,
        fcItemOperations = fcItemOperations,
        fcItemStackFactory = fcItemStackFactory,
    ) {
        override var FcItemStack.type: FcItem
            get() = fcItemFactory.fromMaterial(itemStack.type)
            set(value) {
                itemStack.type = fcItemOperations.bukkit.run { value.material }
            }
    }
}
