package net.benwoodworth.fastcraft.platform.server

import java.nio.file.Path

interface FcPluginData {

    val dataFolder: Path

    val configFile: Path
}