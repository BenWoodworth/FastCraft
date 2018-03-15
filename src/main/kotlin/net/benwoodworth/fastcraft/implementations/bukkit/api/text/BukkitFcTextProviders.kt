package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import javax.inject.Inject
import javax.inject.Provider

class BukkitFcTextProviders<
        TBuilder : BukkitFcText.Builder,
        THelper : BukkitFcText.Helper,
        TActionFactory : BukkitFcTextAction.Factory
        > @Inject constructor(
        val builder: Provider<TBuilder>,
        val helper: Provider<THelper>,
        val clickActionFactory: Provider<TActionFactory>
)