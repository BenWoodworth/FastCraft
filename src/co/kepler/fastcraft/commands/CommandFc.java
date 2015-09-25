package co.kepler.fastcraft.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Handles the command "/fastcraft ..."
 * 
 * @author Kepler_
 */
public class CommandFc extends SubCommand {
	private static final List<String> ADMIN_TOGGLE = list("admin", "craft", "toggle");
	
	private final SubCommand admin = new CommandFcAdmin();
	private final SubCommand craft = new CommandFcCraft();
	private final SubCommand toggle = new CommandFcToggle();
	
	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length == 0) {
			// fastcraft
		} else if (args[0].equalsIgnoreCase("admin")) {
			// fastcraft admin ...
			return admin.onCommand(sender, command, label, args);
		} else if (args[0].equalsIgnoreCase("craft")) {
			// fastcraft craft ...
			return craft.onCommand(sender, command, label, args);
		} else if (args[0].equalsIgnoreCase("toggle")) {
			// fastcraft toggle ...
			return toggle.onCommand(sender, command, label, args);
		}
		showUsage(sender, "<admin|toggle>");
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender,
			Command command, String label, String[] args) {
		List<String> result = null;
		if (args.length == 1) {
			result = getMatches(args[0], ADMIN_TOGGLE);
		} else if (args[0].equalsIgnoreCase("admin")) {
			result = admin.onTabComplete(sender, command, label, args);
		} else if (args[0].equalsIgnoreCase("craft")) {
			result = craft.onTabComplete(sender, command, label, args);
		} else if (args[0].equalsIgnoreCase("toggle")) {
			result = toggle.onTabComplete(sender, command, label, args);
		}
		return result == null ? EMPTY_LIST : result;
	}
}
