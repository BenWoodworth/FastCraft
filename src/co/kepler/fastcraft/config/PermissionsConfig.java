package co.kepler.fastcraft.config;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.Util;
import net.milkbowl.vault.permission.Permission;

/**
 * Manages FastCraft permissions.
 * 
 * @author Kepler_
 */
public class PermissionsConfig {
	public enum FcPerm {
		USE("fastcraft.use"),
		CRAFT("fastcraft.craft"),
		TOGGLE("fastcraft.toggle"),
		TOGGLE_OTHER("fastcraft.toggle.other"),
		ADMIN_RELOAD("fastcraft.admin.reload"),
		ADMIN_UPDATE_CHECK("fastcraft.admin.update.check"),
		ADMIN_UPDATE_DOWNLOAD("fastcraft.admin.update.download"),
		ADMIN_UPDATE_NOTIFICATIONS("fastcraft.admin.update.notifications");

		public final String value;
		private FcPerm(String perm) {
			value = perm;
		}
	}

	private static final String FILENAME = "permissions.yml";
	
	private final File permsFile;
	private final YamlConfiguration config;
	private final String header;
	private Permission permissions;

	@SuppressWarnings("deprecation")
	public PermissionsConfig() {
		config = new YamlConfiguration();
		permsFile = new File(FastCraft.get().getDataFolder(), FILENAME);

		YamlConfiguration resource = YamlConfiguration.loadConfiguration(
				FastCraft.get().getResource(FILENAME));
		header = resource.options().header();
	}

	public void load() {
		try {
			permissions = Bukkit.getServer().getServicesManager().getRegistration(
					net.milkbowl.vault.permission.Permission.class).getProvider();
			if (permsFile.exists()) {
				FastCraft.info("The file permissions.yml is not being used " +
						"and can be deleted. Vault is being used instead.");
			}
			return;
		} catch (NoClassDefFoundError e) {
			permissions = null;
		}
		try {
			if (!permsFile.exists()) {
				FastCraft.get().saveResource(FILENAME, false);
			}
			Util.loadYaml(config, permsFile);
			if (!config.options().header().equals(header)) {
				config.options().header(header);
				config.save(permsFile);
			}
		} catch (Exception e) {
			FastCraft.error(e);
		}
	}

	public boolean playerHas(CommandSender sender, FcPerm permission) {
		if (sender == Bukkit.getConsoleSender()) {
			return true;
		}
		if (permissions != null) {
			return permissions.has(sender, permission.value);
		}

		List<String> userPerms;
		ConfigurationSection users = config.getConfigurationSection("users");
		if (users != null && users.contains(sender.getName())) {
			userPerms = users.getStringList(sender.getName());
		} else {
			userPerms = config.getStringList(sender.isOp() ? "op" : "default");
		}
		return userPerms != null && userPerms.contains(permission.value);
	}
}
