package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

interface FcLogger : Extensible {

    fun error(error: String)

    fun info(info: String)

    fun warn(warning: String)
}
