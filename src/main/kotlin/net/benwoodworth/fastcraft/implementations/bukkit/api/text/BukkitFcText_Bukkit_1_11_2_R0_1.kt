package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcText_Bukkit_1_11_2_R0_1(
        override val text: String
) : BukkitFcText.Legacy by BukkitFcText_Bukkit_1_7_2_R0_3(text) {

    class Builder @Inject constructor(
            delegate: BukkitFcText_Bukkit_1_7_2_R0_3.Builder
    ) : BukkitFcText.Builder by delegate

    class Helper @Inject constructor(
            private val delegate: BukkitFcText_Bukkit_1_7_2_R0_3.Helper
    ) : BukkitFcText.Helper by delegate {

        override fun getItemName(item: ItemStack): BukkitFcText {
            if (item.itemMeta?.hasLocalizedName() == true) {
                return BukkitFcText_Bukkit_1_11_2_R0_1(item.itemMeta.localizedName)
            }

            return delegate.getItemName(item)
        }
    }
}