package net.benwoodworth.fastcraft.platform.config

import java.nio.file.Path

interface FcConfig {
    var headerComment: String?

    operator fun get(key: String): FcConfigNode

    operator fun set(key: String, value: Any?)

    fun save(file: Path)

    interface Factory {
        fun create(): FcConfig

        fun load(file: Path): FcConfig
    }
}
