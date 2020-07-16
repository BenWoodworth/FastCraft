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
        tcItem: FcItem.TypeClass,
    ) : BukkitFcItemStack_1_7.TypeClass(
        items = items,
        textFactory = textFactory,
        tcItem = tcItem,
    ) {
        override val FcItemStack.type: FcItem
            get() = items.fromMaterial(bukkitItemStack.type)
    }
}
