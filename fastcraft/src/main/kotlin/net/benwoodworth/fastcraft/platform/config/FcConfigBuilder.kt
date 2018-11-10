package net.benwoodworth.fastcraft.platform.config

import net.benwoodworth.fastcraft.util.Extensible
import java.nio.file.Path

interface FcConfigBuilder : Extensible {

    fun empty(): Loaded

    fun fromPath(value: Path): Loaded

    interface Loaded : Extensible {

        fun build(): FcConfig
    }
}
