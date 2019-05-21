package net.benwoodworth.fastcraft.bukkit

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BukkitDaggerModule_1_13_00_R01::class])
interface BukkitFastCraftFactory_1_13_00_R01 : BukkitFastCraftFactory
