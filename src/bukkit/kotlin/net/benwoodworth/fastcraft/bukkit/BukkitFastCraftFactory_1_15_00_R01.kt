package net.benwoodworth.fastcraft.bukkit

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BukkitDaggerModule_1_15_00_R01::class])
interface BukkitFastCraftFactory_1_15_00_R01 : BukkitFastCraftFactory
