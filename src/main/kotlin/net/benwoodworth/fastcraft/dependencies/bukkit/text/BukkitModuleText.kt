package net.benwoodworth.fastcraft.dependencies.bukkit.text

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.abstractions.text.ModuleText
import net.benwoodworth.fastcraft.dependencies.abstractions.text.Text
import net.benwoodworth.fastcraft.dependencies.abstractions.text.TextColorRegistry
import javax.inject.Singleton

/**
 * Bukkit implementation of [ModuleText].
 */
@Module
class BukkitModuleText : ModuleText {

    @Provides
    override fun textBuilder(): Text.Builder {
        return BukkitText.Builder()
    }

    @Provides @Singleton
    override fun textColorRegistry(): TextColorRegistry {
        return BukkitTextColorRegistry()
    }
}
