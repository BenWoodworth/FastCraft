package net.benwoodworth.fastcraft.platform.api.config

import java.nio.file.Path

interface FcConfigBuilder {

    fun empty(): Loaded

    fun file(file: Path): Loaded

    interface Loaded {

        fun build(): FcConfig
    }
}
