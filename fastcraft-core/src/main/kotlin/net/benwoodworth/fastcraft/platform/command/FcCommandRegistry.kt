package net.benwoodworth.fastcraft.platform.command

interface FcCommandRegistry {
    fun register(executor: FcCommand, name: String, vararg aliases: String)
}
