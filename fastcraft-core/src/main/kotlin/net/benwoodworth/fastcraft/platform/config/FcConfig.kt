package net.benwoodworth.fastcraft.platform.config

import java.nio.file.Path

interface FcConfig : FcConfigNode {
    var headerComment: String?

    fun save(file: Path)

    interface Factory {
        fun create(): FcConfig

        fun load(file: Path): FcConfig
    }
}
