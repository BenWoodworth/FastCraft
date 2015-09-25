package co.kepler.fastcraft.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.config.LanguageConfig;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;

/**
 * Handles the command "/fastcraft admin ..."
 * 
 * @author Kepler_
 */
public class CommandFcAdmin extends SubCommand {
	private static final List<String> UPDATE_RELOAD = list("update", "reload");

	private final CommandFcAdminUpdate update = new CommandFcAdminUpdate();

	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length == 1) {
			// fastcraft admin
		} else if (args[1].equalsIgnoreCase("update")) {
			// fastcraft admin update ...
			return update.onCommand(sender, command, label, args);
		} else if (args[1].equalsIgnoreCase("reload")) {
			// fastcraft admin reload ...
			return reload(sender, args);
		}
		showUsage(sender, "admin <update|reload>");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length == 2) {
			// fastcraft admin (tab)
			return getMatches(args[1], UPDATE_RELOAD);
		} else if (args[1].equalsIgnoreCase("update")) {
			// fastcraft admin update (tab)
			return update.onTabComplete(sender, command, label, args);
		}
		return null;
	}

	/**
	 * Handles the command "/fastcraft admin reload ..."
	 * @param sender The command sender.
	 * @param args The command arguments.
	 * @return Returns true if the command runs successfully.
	 */
	private boolean reload(CommandSender sender, String[] args) {
		LanguageConfig lang = FastCraft.configs().lang;
		
		// Invalid if too many arguments
		if (args.length > 2) {
			showUsage(sender, "admin reload");
			return false;
		}
		
		// Check for reload permission
		FcPerm permission = FcPerm.ADMIN_RELOAD;
		if (!FastCraft.configs().permissions.playerHas(sender, permission)) {
			sender.sendMessage(lang.errNoPerm(permission));
			return true;
		}
		
		// Reload FastCraft
		FastCraft.get().reload();
		sender.sendMessage(lang.outFcAdminReload());
		return true;
	}
}
