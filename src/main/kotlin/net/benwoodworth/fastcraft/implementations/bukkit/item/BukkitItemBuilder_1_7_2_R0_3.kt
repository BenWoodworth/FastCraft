package net.benwoodworth.fastcraft.implementations.bukkit.item

import org.bukkit.Bukkit

open class BukkitItemBuilder_1_7_2_R0_3 : BukkitItemBuilder_1_5_R0_1() {

    override fun type(typeId: String) = also {
        @Suppress("DEPRECATION")
        result.type = Bukkit.getUnsafe().getMaterialFromInternalName(typeId)
    }
}