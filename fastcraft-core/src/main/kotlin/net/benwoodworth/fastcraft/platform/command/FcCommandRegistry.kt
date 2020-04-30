package net.benwoodworth.fastcraft.platform.command

interface FcCommandRegistry {
    fun register(command: FcCommand, name: String, vararg aliases: String)
}
