package net.benwoodworth.fastcraft.platform.config

import java.nio.file.Path

interface FcConfig {

    var header: String?

    fun save(path: Path)

    operator fun get(key: String): FcConfigEntry
}
