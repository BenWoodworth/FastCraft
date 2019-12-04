package net.benwoodworth.fastcraft.platform.server

interface FcLogger {

    fun error(error: String)

    fun info(info: String)

    fun warn(warning: String)
}
