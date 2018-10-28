package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible
import java.nio.file.Path

interface FcPluginData : Extensible {

    val dataFolder: Path

    val configFile: Path
}