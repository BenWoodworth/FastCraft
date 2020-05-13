package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemStack
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcItemStack_1_13(
    itemStack: ItemStack,
    materials: FcMaterial.Factory,
    textFactory: FcText.Factory,
) : BukkitFcItemStack_1_7(
    bukkitItemStack = itemStack,
    materials = materials,
    textFactory = textFactory
) {
    override val type: FcMaterial
        get() = materials.fromMaterial(bukkitItemStack.type)

    @Singleton
    open class Factory @Inject constructor(
        materials: FcMaterial.Factory,
        textFactory: FcText.Factory,
        server: Server,
    ) : BukkitFcItemStack_1_7.Factory(
        materials = materials,
        textFactory = textFactory,
        server = server,
    ) {
        override fun create(itemStack: ItemStack): FcItemStack {
            return BukkitFcItemStack_1_13(
                itemStack = itemStack,
                materials = materials,
                textFactory = textFactory
            )
        }
    }
}
