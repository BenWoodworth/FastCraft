package co.kepler.fastcraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import co.kepler.fastcraft.config.LanguageConfig;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;
import co.kepler.fastcraft.config.PluginConfig;

/**
 * Handles events for FastCraft.
 * 
 * @author Kepler_
 */
public class EventListener implements Listener {
	
	/**
	 * Prevent FastCraft from using its interface when commands in the
	 * compatibility list are run.
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onCommandProcess(PlayerCommandPreprocessEvent event) {
		PluginConfig config = FastCraft.configs().config;
		InventoryManager invManager = FastCraft.get().getInventoryManager();
		String command = event.getMessage();
		if (!event.isCancelled() && !config.isCommandCompatible(command)) {
			// Disable FastCraft temporarily if command is not compatible
			invManager.tempDisable(event.getPlayer());
		}
	}

	/**
	 * Notify players of updates when they join the server.
	 */
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player p = event.getPlayer();
		FastCraft.configs().players.updatePlayerID(p);
		
		LanguageConfig lang = FastCraft.configs().lang;
		PluginUpdater updater = FastCraft.get().getPluginUpdater();
		if (!updater.isUpdateAvailable()) {
			return;
		}
		
		FcPerm perm = FcPerm.ADMIN_UPDATE_NOTIFICATIONS;
		final String message = lang.updateNotification(updater.getNewVersion());
		if (FastCraft.configs().permissions.playerHas(p, perm)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(FastCraft.get(), new Runnable() {
				@Override
				public void run() {
					p.sendMessage(message);
				}
			}, 20);
		}
	}
}
