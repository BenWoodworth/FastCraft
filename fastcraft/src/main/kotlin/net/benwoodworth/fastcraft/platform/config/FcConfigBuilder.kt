package net.benwoodworth.fastcraft.platform.config

import net.benwoodworth.fastcraft.util.Extensible
import java.nio.file.Path

interface FcConfigBuilder : Extensible {

    fun empty(): FcConfigBuilderLoaded

    fun file(file: Path): FcConfigBuilderLoaded
}
