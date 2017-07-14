package net.benwoodworth.fastcraft.impl.sponge.text

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.text.ModuleText
import net.benwoodworth.fastcraft.dependencies.text.TextBuilder
import net.benwoodworth.fastcraft.dependencies.text.TextColorRegistry
import javax.inject.Singleton
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [ModuleText].
 */
@Module
class SpongeModuleText : ModuleText {

    @Provides
    override fun textBuilder(): TextBuilder {
        return SpongeTextBuilder(Sponge_Text.builder(""))
    }

    @Provides @Singleton
    override fun textColorRegistry(): TextColorRegistry {
        return SpongeTextColorRegistry()
    }
}
