package net.benwoodworth.fastcraft.bukkit.api

import net.benwoodworth.fastcraft.api.FastCraftApi
import net.benwoodworth.fastcraft.api.FastCraftApiImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFastCraftApiImpl @Inject constructor(
    fastCraftApi: FastCraftApiImpl,
    override val preferences: BukkitFastCraftPreferencesImpl,
) : BukkitFastCraftApi, FastCraftApi by fastCraftApi
