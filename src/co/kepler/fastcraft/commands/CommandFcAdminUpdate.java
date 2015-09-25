package co.kepler.fastcraft.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.config.LanguageConfig;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;

/**
 * Handles the command "/fastcraft admin update ..."
 * 
 * @author Kepler_
 */
public class CommandFcAdminUpdate extends SubCommand {
	private static final List<String> CHECK_DOWNLOAD = list("check", "download");
	
	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length == 2) {
			// fastcraft admin update
		} else if (args[2].equalsIgnoreCase("check")) {
			// fastcraft admin update check ...
			return check(sender, args);
		} else if (args[2].equalsIgnoreCase("download")) {
			// fastcraft admin update download ...
			return download(sender, args);
		}
		showUsage(sender, "admin update <check|download>");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length == 3) {
			// fastcraft admin update (tab)
			return getMatches(args[2], CHECK_DOWNLOAD);
		}
		return null;
	}
	
	/**
	 * Handles the command "/fastcraft admin update check ..."
	 * @param sender The command sender.
	 * @param args The command arguments.
	 * @return Returns true if the command runs successfully.
	 */
	private boolean check(CommandSender sender, String[] args) {
		LanguageConfig lang = FastCraft.configs().lang;
		
		// Check for update check permission
		FcPerm permission = FcPerm.ADMIN_UPDATE_CHECK;
		if (!FastCraft.configs().permissions.playerHas(sender, permission)) {
			sender.sendMessage(lang.errNoPerm(permission));
			return true;
		}
		
		// Check for invalid command
		if (args.length > 3) {
			showUsage(sender, "admin update check");
			return true;
		}
		
		// Check for update
		FastCraft.get().getPluginUpdater().checkUpdate(sender, false);
		return true;
	}
	
	
	/**
	 * Handles the command "/fastcraft admin update download ..."
	 * @param sender The command sender.
	 * @param args The command arguments.
	 * @return Returns true if the command runs successfully.
	 */
	private boolean download(CommandSender sender, String[] args) {
		LanguageConfig lang = FastCraft.configs().lang;
		
		// Check for update download permission
		FcPerm permission = FcPerm.ADMIN_UPDATE_DOWNLOAD;
		if (!FastCraft.configs().permissions.playerHas(sender, permission)) {
			sender.sendMessage(lang.errNoPerm(permission));
			return true;
		}
		
		// Check for invalid command
		if (args.length > 3) {
			showUsage(sender, "admin update download");
			return true;
		}
		
		// Download update
		FastCraft.get().getPluginUpdater().downloadUpdate(sender, true);
		return true;
	}
}
