package net.benwoodworth.fastcraft.platform.server

interface FcServer {
    val minecraftVersion: String

    fun executeCommand(command: String)
}
