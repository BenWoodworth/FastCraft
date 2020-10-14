package net.benwoodworth.fastcraft.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FastCraftApiImpl @Inject constructor(
    override val preferences: FastCraftPreferences,
) : FastCraftApi
