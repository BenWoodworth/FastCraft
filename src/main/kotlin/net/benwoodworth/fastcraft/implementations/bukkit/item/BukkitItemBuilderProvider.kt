package net.benwoodworth.fastcraft.implementations.bukkit.item

import net.benwoodworth.fastcraft.dependencies.DependencyProvider
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitApiVersion
import javax.inject.Inject

class BukkitItemBuilderProvider @Inject constructor(
        version: BukkitApiVersion
) : DependencyProvider<BukkitApiVersion, ItemBuilder>(version,
        BukkitApiVersion.parse("1.7.2-R0.3")!! to { BukkitItemBuilder_1_7_2_R0_3() },
        BukkitApiVersion.parse("1.5-R0.1")!! to { BukkitItemBuilder_1_5_R0_1() }
)