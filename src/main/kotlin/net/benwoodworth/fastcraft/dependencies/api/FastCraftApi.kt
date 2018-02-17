package net.benwoodworth.fastcraft.dependencies.api

import net.benwoodworth.fastcraft.core.FastCraft
import javax.inject.Inject

/**
 * A FastCraft API for use by other plugins.
 */
class FastCraftApi @Inject constructor(
        /**
         * The instance of FastCraft.
         */
        val fastCraft: FastCraft
) {

//    /** TODO
//     * Checks to see if an inventory is a GUI.
//     *
//     * @param inventory the inventory to check
//     * @return `true` iff the inventory is a GUI
//     */
//    fun isGuiInventory(inventory: TInventory): Boolean {
//        val fcInv = fastCraft.dependencies.inventoryProvider.adaptInventory(inventory)
//        val carrier = fcInv.carrier
//        return carrier is Gui<*, *>
//    }
}
