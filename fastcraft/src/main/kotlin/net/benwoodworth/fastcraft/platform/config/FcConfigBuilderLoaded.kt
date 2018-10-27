package net.benwoodworth.fastcraft.platform.config

import net.benwoodworth.fastcraft.util.Extensible

interface FcConfigBuilderLoaded : Extensible {

    fun build(): FcConfig
}
