package co.kepler.fastcraft.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.config.LanguageConfig;
import co.kepler.fastcraft.config.PermissionsConfig;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;
import co.kepler.fastcraft.config.PlayersConfig;

/**
 * Handles the command "/fastcraft toggle ..."
 * 
 * @author Kepler_
 */
public class CommandFcToggle extends SubCommand {
	private static final List<String> ON_OFF_TOGGLE = list("on", "off", "toggle");
	
	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {
		boolean result = false;
		if (args.length == 1) {
			// fastcraft toggle
			result = toggle(sender, null, null);
		} else if (args.length == 2) {
			// fastcraft toggle [on/off/toggle]
			result = toggle(sender, args[1], null);
		} else if (args.length == 3) {
			// fastcraft toggle [on/off/toggle] [player]
			result = toggle(sender, args[1], args[2]);
		}
		if (!result) {
			showUsage(sender, "toggle [on|off|toggle] [player]");
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender,
			Command command, String label, String[] args) {
		if (args.length == 2) {
			// fastcraft toggle (tab)
			return getMatches(args[1], ON_OFF_TOGGLE);
		} else if (args.length == 3) {
			// fastcraft toggle [on/off/toggle] (tab)
			return listPlayers(args[2]);
		}
		return null;
	}

	/**
	 * Toggle FastCraft for a player.
	 * @param sender The command sender.
	 * @param value How to toggle (on, off, or toggle).
	 * @param player The player to toggle.
	 * @return Returns true if the player is toggled successfully.
	 */
	private boolean toggle(CommandSender sender, String value, String player) {
		LanguageConfig lang = FastCraft.configs().lang;
		PermissionsConfig perms = FastCraft.configs().permissions;
		PlayersConfig players = FastCraft.configs().players;

		// Check for toggle permission
		if (!perms.playerHas(sender, FcPerm.TOGGLE)) {
			sender.sendMessage(lang.errNoPerm(FcPerm.TOGGLE));
			return true;
		}

		// Get the player to toggle
		Player togglePlayer;
		if (player == null) {
			// Sender is toggling self
			if (sender instanceof Player) {
				togglePlayer = (Player) sender;
			} else {
				sender.sendMessage(lang.errCmdPlayerOnly());
				return false;
			}
		} else {
			// Sender is toggling another player
			togglePlayer = FastCraft.configs().players.getPlayer(player);
			if (togglePlayer == null) {
				// Entered an invalid player name
				sender.sendMessage(lang.errPlayerNotFound(player));
				return false;
			} else if (sender.equals(togglePlayer)) {
				// Sender is toggling self
			} else if (!perms.playerHas(sender, FcPerm.TOGGLE_OTHER)) {
				// Isn't allowed to toggle another player
				sender.sendMessage(lang.errNoPerm(FcPerm.TOGGLE_OTHER));
				return true;
			} 
		}

		// Toggle FastCraft for togglePlayer
		boolean enabled = true;
		if (value == null || value.equalsIgnoreCase("toggle")) {
			enabled = players.toggleFastCraftEnabled(togglePlayer);
		} else if (value.equalsIgnoreCase("on")) {
			players.setFastCraftEnabled(togglePlayer, true);
		} else if (value.equalsIgnoreCase("off")) {
			enabled = false;
			players.setFastCraftEnabled(togglePlayer, false);
		} else {
			return false;
		}
		
		// Send player(s) toggle message
		if (enabled) {
			togglePlayer.sendMessage(lang.outFcToggleOn());
			if (!sender.equals(togglePlayer)) {
				sender.sendMessage(lang.outFcToggleOnOther(togglePlayer.getName()));
			}
		} else {
			togglePlayer.sendMessage(lang.outFcToggleOff());
			if (!sender.equals(togglePlayer)) {
				sender.sendMessage(lang.outFcToggleOffOther(togglePlayer.getName()));
			}
		}
		return true;
	}
}
