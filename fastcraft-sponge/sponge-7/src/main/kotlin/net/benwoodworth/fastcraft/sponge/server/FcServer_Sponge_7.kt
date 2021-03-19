package net.benwoodworth.fastcraft.sponge.server

import org.spongepowered.api.Platform
import org.spongepowered.api.Server
import org.spongepowered.api.command.CommandManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcServer_Sponge_7 @Inject constructor(
    private val server: Server,
    private val platform: Platform,
    private val commandManager: CommandManager,
) : FcServer_Sponge {
    override val minecraftVersion: String
        get() = platform.getContainer(Platform.Component.GAME).version.get()

    override fun executeCommand(command: String) {
        commandManager.process(server.console, command)
    }
}
