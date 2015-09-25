package co.kepler.fastcraft.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;

public class CommandFcCraft extends SubCommand {
	private final FcPerm perm = FcPerm.CRAFT;
	
	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length > 1) {
			showUsage(sender, "craft");
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(FastCraft.configs().lang.errCmdPlayerOnly());
		} else if (!FastCraft.configs().permissions.playerHas(sender, perm)){
			sender.sendMessage(FastCraft.configs().lang.errNoPerm(perm));
		} else {
			((Player) sender).openWorkbench(null, true);
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender,
			Command command, String label, String[] args) {
		return null;
	}
}
