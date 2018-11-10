package net.benwoodworth.fastcraft.platform.config

import net.benwoodworth.fastcraft.util.Extensible
import java.nio.file.Path

interface FcConfig : Extensible {

    var header: String?

    fun save(path: Path)

    operator fun get(key: String): FcConfigEntry
}
