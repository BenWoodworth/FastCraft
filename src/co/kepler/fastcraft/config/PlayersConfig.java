package co.kepler.fastcraft.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.Util;

/**
 * Manages FastCraft's player preferences.
 * 
 * @author Kepler_
 */
public class PlayersConfig {
	private final String fastCraftEnabled = "fastCraftEnabled";
	
	private final File playersFile;
	private final YamlConfiguration config;
	
	public PlayersConfig() {
		playersFile = new File(FastCraft.get().getDataFolder(), "players.yml");
		config = new YamlConfiguration();
	}
	
	public void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
		playersFile.createNewFile();
		Util.loadYaml(config, playersFile);
	}
	
	public void save() throws IOException {
		config.save(playersFile);
	}
	
	Boolean canUseUUID = null;
	private boolean canUseUUID() {
		if (canUseUUID != null) {
			return canUseUUID;
		}
		try {
			Player.class.getMethod("getUniqueId");
			canUseUUID = true;
		} catch (Exception e) {
			canUseUUID = false;
		}
		return canUseUUID;
	}
	
	public String getID(Player p) {
		if (canUseUUID()) {
			return p.getUniqueId().toString();
		}
		return p.getName();
	}
	
	/**
	 * If players.yml contains the player's name, and a UUID can be used,
	 * then update the config to use the UUID instead.
	 * @param p The player to update.
	 */
	public void updatePlayerID(Player p) {
		if (canUseUUID() && config.contains(p.getName())) {
			String uuid = p.getUniqueId().toString();
			if (config.contains(uuid)) {
				config.set(uuid, null);
			} else {
				ConfigurationSection s = config.getConfigurationSection(p.getName());
				config.createSection(uuid, s.getValues(true));
				config.set(p.getName(), null);
			}
		}
	}
	
	private boolean getPlayerPreference(Player p, String pref, boolean def) {
		return config.getBoolean(getID(p) + "." + pref, def);
	}
	
	private void setPlayerPreference(Player p, String pref, boolean b) {
		config.set(getID(p) + "." + pref, b);
	}
	
	private boolean togglePlayerPreference(Player p, String pref, boolean def) {
		boolean result = !getPlayerPreference(p, pref, def);
		setPlayerPreference(p, pref, result);
		return result;
	}
	
	public Player getOnlinePlayerFromID(String identifier) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (getID(p).equals(identifier)) {
				return p;
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public Player getPlayer(String name) {
		return Bukkit.getPlayer(name);
	}
	
	/**
	 * FastCraft default enabled
	 * @return Returns whether FastCraftDefaultEnabled is true or false
	 */
	private boolean fcde()
	{ return FastCraft.configs().config.fastCraftDefaultEnabled(); }
	
	public boolean getFastCraftEnabled(Player p)
	{ return getPlayerPreference(p, fastCraftEnabled, fcde()); }
	
	public void setFastCraftEnabled(Player p, boolean b)
	{ setPlayerPreference(p, fastCraftEnabled, b); }
	
	public boolean toggleFastCraftEnabled(Player p)
	{ return togglePlayerPreference(p, fastCraftEnabled, fcde()); }
}
