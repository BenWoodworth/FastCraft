package net.benwoodworth.fastcraft.platform.config

import java.nio.file.Path

interface FcConfigFactory {

    fun createEmpty(): FcConfig

    fun createFromPath(value: Path): FcConfig
}
