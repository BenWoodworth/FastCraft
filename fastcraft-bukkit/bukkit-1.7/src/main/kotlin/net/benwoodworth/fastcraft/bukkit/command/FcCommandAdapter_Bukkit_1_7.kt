package net.benwoodworth.fastcraft.bukkit.command

import net.benwoodworth.fastcraft.platform.command.FcCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import javax.inject.Inject
import javax.inject.Singleton

class FcCommandAdapter_Bukkit_1_7(
    override val command: FcCommand,
    private val commandSourceFactory: FcCommandSource_Bukkit.Factory,
) : FcCommandAdapter_Bukkit {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>,
    ): Boolean {
        this.command.process(
            source = commandSourceFactory.create(sender),
            arguments = args.joinToString(" "),
        )

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>,
    ): List<String> {
        return this.command.getSuggestions(
            source = commandSourceFactory.create(sender),
            arguments = args.joinToString(" "),
        )
    }

    @Singleton
    class Factory @Inject constructor(
        private val fcCommandSourceFactory: FcCommandSource_Bukkit.Factory,
    ) : FcCommandAdapter_Bukkit.Factory {
        override fun create(command: FcCommand): FcCommandAdapter_Bukkit {
            return FcCommandAdapter_Bukkit_1_7(
                command = command,
                commandSourceFactory = fcCommandSourceFactory,
            )
        }
    }
}
