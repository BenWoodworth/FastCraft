package co.kepler.fastcraft.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import co.kepler.fastcraft.FastCraft;

/**
 * Holds and maintains FastCraft's configurations.
 * 
 * @author Kepler_
 */
public class ConfigManager {
	public final PluginConfig config;
	public final LanguageConfig lang;
	public final PermissionsConfig permissions;
	public final PlayersConfig players;

	/**
	 * Updates and loads the configs.
	 */
	public ConfigManager() {
		updateConfigs();
		config = new PluginConfig();
		lang = new LanguageConfig();
		permissions = new PermissionsConfig();
		players = new PlayersConfig();
	}

	/**
	 * Reload the config files.
	 */
	public void loadConfigs() {
		try {
			config.load();
			lang.load(config.language());
			permissions.load();
			players.load();
		} catch (Exception e) {
			FastCraft.error(e);
		}
	}

	/**
	 * Save the configs that need saving.
	 * @throws IOException Thrown if there is an error saving.
	 */
	public void saveConfigs() {
		// Only save the players config. No others are modified
		try {
			players.save();
		} catch (IOException e) {
			FastCraft.error(e);
		}
	}

	/**
	 * Update the configs to be compatible with the new FastCraft version.
	 */
	public void updateConfigs() {
		// Get version from last FastCraft startup
		int lastVersion = FastCraft.get().getVersion();
		File versionFile = new File(FastCraft.get().getDataFolder(),"version.yml");
		File configFile = new File(FastCraft.get().getDataFolder(), "config.yml");
		YamlConfiguration config = null;
		if (versionFile.exists()) {
			lastVersion = YamlConfiguration
					.loadConfiguration(versionFile).getInt("version");
		} else if (configFile.exists()) {
			config = YamlConfiguration.loadConfiguration(configFile);
			lastVersion = config.getInt("version-DoNotEdit", lastVersion);
		}

		// Update configuration file
		rnFile("playerPrefs.yml", "players.yml");
		rnFile("ingredients.yml", "items.yml");
		if (config != null && lastVersion <= 15) {
			rnKey(config, "buttonPrevItem", "interfaceItems.prevPage");
			rnKey(config, "buttonNextItem", "interfaceItems.nextPage");
			rnKey(config, "buttonHelpItem", "interfaceItems.help");
			rnKey(config, "buttonCraftItem", "interfaceItems.craft");
			rnKey(config, "buttonRefreshItem", "interfaceItems.refresh");
			rnKey(config, "disabledRecipes", "recipes.disabledResults");
			rnKey(config, "disabledIngredients", "recipes.disabledIngredients");

			rmList(config, "recipes.disabledResults", "358", "401");
			rmList(config, "recipes.disabledIngredients", "358", "395", "403");
		}
		if (lastVersion <= 19) {
			File itemsFile = new File(FastCraft.get().getDataFolder(), "items.yml");
			FastCraft.info("Update: Deleting items.yml");
			itemsFile.delete(); // TODO Check if exists?
		}

		// Save config, and version.yml with the current FastCraft version
		YamlConfiguration versionConfig = new YamlConfiguration();
		versionConfig.set("version", FastCraft.get().getVersion());
		versionConfig.options().header("This file is for use by the FastCraft plugin. Do not edit!");
		try {
			if (config != null) {
				config.save(configFile);
			}
			versionConfig.save(versionFile);
		} catch (IOException e) {
			FastCraft.error(e);
		}
	}

	/**
	 * Rename an entry in the given config.
	 * @param config The config in which to rename a key.
	 * @param from The current key name.
	 * @param to The new key name.
	 */
	private void rnKey(YamlConfiguration config, String from, String to) {
		if (!config.contains(to)) {
			config.set(to, config.get(from));
			FastCraft.info("Update: moving \"" +
					from + "\" to \"" + to + "\" in config");
		}
		config.set(from, null);
	}

	/**
	 * Remove the specified values from a list.
	 * @param config The config to modify.
	 * @param key The key to remove values from.
	 * @param vals The values to remove.
	 */
	private void rmList(YamlConfiguration config, String key, String... vals) {
		List<String> list = config.getStringList(key);
		for (String s : vals) {
			if (list.contains(s)) {
				list.remove(s);
				FastCraft.info("Update: removing \"" + s
						+ "\" from \"" + key + "\" in config");
			}
		}
		config.set(key, list);
	}

	/**
	 * Rename a file in the FastCraft directory.
	 * @param from The current filename.
	 * @param to The new filename.
	 */
	private void rnFile(String from, String to) {
		File dataFolder = FastCraft.get().getDataFolder();
		File fromFile = new File(dataFolder, from);
		if (fromFile.exists()) {
			File toFile = new File(dataFolder, to);
			if (toFile.exists()) {
				fromFile.delete();
				FastCraft.info("Update: deleting \"" + from + "\"");
			} else {
				fromFile.renameTo(toFile);
				FastCraft.info("Update: renaming \"" + from + "\" to \"" + to + "\"");
			} 
		}
	}
}
