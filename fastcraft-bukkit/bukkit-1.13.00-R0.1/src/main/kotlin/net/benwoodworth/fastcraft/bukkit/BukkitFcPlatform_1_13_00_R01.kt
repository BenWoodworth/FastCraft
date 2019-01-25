package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGuiFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerProvider_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcRecipeService_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskFactory_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextFactory_1_13_00_R01
import javax.inject.Inject

class BukkitFcPlatform_1_13_00_R01 @Inject constructor(
    override val logger: BukkitFcLogger_1_13_00_R01,
    override val pluginData: BukkitFcPluginData_1_13_00_R01,
    override val configFactory: BukkitFcConfigFactory_1_13_00_R01,
    override val guiFactory: BukkitFcGuiFactory_1_13_00_R01,
    override val itemFactory: BukkitFcItemFactory_1_13_00_R01,
    override val itemTypes: BukkitFcItemTypes_1_13_00_R01,
    override val playerProvider: BukkitFcPlayerProvider_1_13_00_R01,
    override val recipeService: BukkitFcRecipeService_1_13_00_R01,
    override val taskFactory: BukkitFcTaskFactory_1_13_00_R01,
    override val textFactory: BukkitFcTextFactory_1_13_00_R01
) : BukkitFcPlatform
