package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class ItemStackNameProvider_1_13_00_R01 @Inject constructor(
    private val textFactory: FcTextFactory,
    private val itemTypes: FcItemTypes
) : ItemStackNameProvider {
    override fun ItemStack.getName(): FcText {
        with(textFactory) {
            return this@getName
                .takeIf { it.hasItemMeta() }
                ?.itemMeta
                ?.takeIf { it.hasDisplayName() }
                ?.displayName
                ?.let { createFcText(it) }
                ?: itemTypes.fromMaterial(type).name
        }
    }
}
