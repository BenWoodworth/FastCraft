package net.benwoodworth.fastcraft.platform.api.config

import java.nio.file.Path

interface FcConfigBuilder {

    fun empty(): FcConfigBuilderLoaded

    fun file(file: Path): FcConfigBuilderLoaded
}
