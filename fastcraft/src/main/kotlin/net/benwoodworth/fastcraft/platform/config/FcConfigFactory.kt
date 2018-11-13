package net.benwoodworth.fastcraft.platform.config

import net.benwoodworth.fastcraft.util.Extensible
import java.nio.file.Path

interface FcConfigFactory : Extensible {

    fun createEmpty(): FcConfig

    fun createFromPath(value: Path): FcConfig
}
