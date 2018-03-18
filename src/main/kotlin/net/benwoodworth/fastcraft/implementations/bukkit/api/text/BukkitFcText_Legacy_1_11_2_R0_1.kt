package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import org.bukkit.inventory.ItemStack
import javax.inject.Inject

@Suppress("ClassName")
class BukkitFcText_Legacy_1_11_2_R0_1(
        text: String
) : BukkitFcText_Legacy by BukkitFcText_Legacy_1_7_2_R0_3(text) {

    class Factory @Inject constructor(
            private val delegate: BukkitFcText_Legacy_1_7_2_R0_3.Factory
    ) : BukkitFcText.Factory by delegate {

        override fun getItemName(item: ItemStack): BukkitFcText {
            if (item.itemMeta?.hasLocalizedName() == true) {
                return BukkitFcText_Legacy_1_11_2_R0_1(item.itemMeta.localizedName)
            }

            return delegate.getItemName(item)
        }
    }
}