package net.benwoodworth.fastcraft.implementations.bukkit.item

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ModuleItem
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitApiVersion
import net.benwoodworth.fastcraft.implementations.bukkit.UnsupportedBukkitApiException
import net.benwoodworth.fastcraft.implementations.bukkit.item.recipe.BukkitRecipeProvider

/**
 * Bukkit implementation of [ModuleItem].
 */
@Module
class BukkitModuleItem : ModuleItem {

    @Provides
    override fun itemBuilder(): ItemBuilder = when {
        BukkitApiVersion.current >= BukkitApiVersion.parse("1.7.2-R0.3")!! ->
            BukkitItemBuilder.Api_1_7_2_R0_3()

        BukkitApiVersion.current >= BukkitApiVersion.parse("1.5-R0.1")!! ->
            BukkitItemBuilder.Api_1_5_R0_1()

        else -> throw UnsupportedBukkitApiException()
    }

    @Provides
    override fun recipeProvider(): RecipeProvider {
        return BukkitRecipeProvider()
    }
}
