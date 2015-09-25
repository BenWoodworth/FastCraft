package co.kepler.fastcraft;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import co.kepler.fastcraft.config.LanguageConfig;
import co.kepler.fastcraft.config.PluginConfig;
import co.kepler.fastcraft.libraries.Updater;
import co.kepler.fastcraft.libraries.Updater.ReleaseType;
import co.kepler.fastcraft.libraries.Updater.UpdateCallback;
import co.kepler.fastcraft.libraries.Updater.UpdateType;

/**
 * Handles update checks and downloads.
 * 
 * @author Kepler_
 */
public class PluginUpdater {
	private static final int PLUGIN_ID = 63587;

	private String newVersion = null;
	private boolean downloaded = false;

	private int taskID = -1;
	private File pluginFile;

	/**
	 * Create a new instance of PluginUpdater.
	 * @param pluginFile The jar file FastCraft is in.
	 */
	public PluginUpdater(File pluginFile) {
		this.pluginFile = pluginFile;

		if (taskID != -1) {
			Bukkit.getScheduler().cancelTask(taskID);
			taskID = -1;
		}

		PluginConfig config = FastCraft.configs().config;
		if (config.autoUpdate_enabled()) {
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(
					FastCraft.get(), new Runnable() {
						public void run() {
							checkUpdate(null, true);
						}
					}, 100, config.autoUpdate_checkInterval() * 1200);
		}
	}

	/**
	 * Check for an update.
	 * @param sender Who is checking for the update.
	 * @param download Download available updates if true.
	 */
	public void checkUpdate(CommandSender sender, boolean download) {
		LanguageConfig lang = FastCraft.configs().lang;
		if (downloaded) {
			send(sender, lang.outFcAdminUpdate_Downloaded(newVersion));
			return;
		} else if (newVersion != null) {
			send(sender, lang.outFcAdminUpdate_Available(newVersion));
			return;
		}

		send(sender, lang.outFcAdminUpdate_Checking());
		new Updater(FastCraft.get(), PLUGIN_ID, pluginFile,
				UpdateType.NO_DOWNLOAD, new Callback(sender, download), false);
	}

	/**
	 * Download an update if available.
	 * @param sender Who is downloading the update.
	 * @param versionCheck Whether to have a version check before downloading.
	 */
	public void downloadUpdate(CommandSender sender, boolean versionCheck) {
		LanguageConfig lang = FastCraft.configs().lang;
		if (downloaded) {
			send(sender, lang.outFcAdminUpdate_Downloaded(newVersion));
			return;
		}

		UpdateType type = versionCheck ? UpdateType.DEFAULT : UpdateType.NO_VERSION_CHECK;
		Callback callback = new Callback(sender);

		send(sender, lang.outFcAdminUpdate_Downloading());
		new Updater(FastCraft.get(), PLUGIN_ID, pluginFile, type, callback, true);
	}

	/**
	 * Get the name of the new FastCraft update.
	 * @return Returns the name of the new FastCraft update.
	 */
	public String getNewVersion() {
		return newVersion;
	}

	/**
	 * Check if there is a new version that hasn't been downloaded yet.
	 * @return
	 */
	public boolean isUpdateAvailable() {
		return newVersion != null && !downloaded;
	}

	/**
	 * Send message to sender.
	 * @param sender Sender to send message to.
	 * @param message Message to send.
	 */
	private void send(CommandSender sender, String message) {
		if (sender != null) {
			sender.sendMessage(message);
		} else if (FastCraft.configs().config.autoUpdate_showConsoleMessages()) {
			FastCraft.info(message);
		}
	}

	/**
	 * Callback class to handle updates.
	 * 
	 * @author Kepler_
	 */
	private class Callback implements UpdateCallback {
		private final CommandSender sender;
		private final boolean download;

		/**
		 * Callback constructor for checking for an update.
		 * @param sender The sender to give update details to.
		 * @param download Whether or not to download available updates.
		 */
		public Callback(CommandSender sender, boolean download) {
			this.sender = sender;
			this.download = download;
		}

		/**
		 * Callback constructor for downloading an update.
		 * @param sender The sender to give update details to.
		 */
		public Callback(CommandSender sender) {
			this.sender = sender;
			download = false;
		}

		@Override
		public void onFinish(Updater updater) {
			LanguageConfig lang = FastCraft.configs().lang;
			PluginConfig config = FastCraft.configs().config;
			switch (updater.getResult()) {
			case NO_UPDATE:
				send(sender, lang.outFcAdminUpdate_None());
				break;
			case UPDATE_AVAILABLE:
				send(sender, lang.outFcAdminUpdate_Available(updater.getLatestName()));
				newVersion = updater.getLatestName();
				if (taskID != -1) {
					Bukkit.getScheduler().cancelTask(taskID);
					taskID = -1;
				}
				if (download && config.autoUpdate_autoDownload() && (
						config.autoUpdate_devBuilds() ||
						updater.getLatestType() == ReleaseType.RELEASE)) {
					downloadUpdate(sender, false);
				}
				break;
			case FAIL_DOWNLOAD:
				send(sender, lang.outFcAdminUpdate_DlErr(updater.getLatestName()));
				break;
			case SUCCESS:
				send(sender, lang.outFcAdminUpdate_Complete(updater.getLatestName()));
				downloaded = true;
				break;
			default: // FAIL_DBO, FAIL_NOVERSION, ...
				send(sender, lang.outFcAdminUpdate_ErrFetch());
				break;
			}
		}
	}
}