package co.kepler.fastcraft;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import co.kepler.fastcraft.commands.CommandFc;
import co.kepler.fastcraft.config.ConfigManager;
import co.kepler.fastcraft.recipe.RecipeUtil;

/**
 * Main class in FastCraft.
 * 
 * @author Kepler_
 */
public class FastCraft extends JavaPlugin {
	private static FastCraft fastCraft;

	private HashMap<Material, Achievement> achievements = new HashMap<Material, Achievement>();
	private InventoryManager inventoryManager;
	private ConfigManager configManager;
	private PluginUpdater pluginUpdater;
	private int version;
	
	public RecipeUtil recipeUtil;

	@Override
	public void onEnable() {
		fastCraft = this;
		version = Integer.parseInt(getDescription().getVersion());

		configManager = new ConfigManager();
		configManager.loadConfigs();

		Bukkit.getPluginCommand("fastcraft").setExecutor(new CommandFc());

		inventoryManager = new InventoryManager();
		
		MetricsStarter.start(this);

		pluginUpdater = new PluginUpdater(getFile());
		recipeUtil = RecipeUtil.setup();

		setupAchievements();

		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(inventoryManager, this);
		manager.registerEvents(new EventListener(), this);
	}

	@Override
	public void onDisable() {
		inventoryManager.closeInventories();
		configs().saveConfigs();
	}

	public void reload() {
		inventoryManager.closeInventories();
		configs().saveConfigs();
		configs().loadConfigs();
	}

	public void setupAchievements() {
		addAchievement(Material.STONE_PICKAXE, "BUILD_BETTER_PICKAXE");
		addAchievement(Material.FURNACE, "BUILD_FURNACE");
		addAchievement(Material.WOOD_HOE, "BUILD_HOE");
		addAchievement(Material.WOOD_PICKAXE, "BUILD_PICKAXE");
		addAchievement(Material.WOOD_SWORD, "BUILD_SWORD");
		addAchievement(Material.WORKBENCH, "BUILD_WORKBENCH");
	}

	public void addAchievement(Material m, String achievement) {
		try {
			achievements.put(m, Achievement.valueOf(achievement));
		} catch (Exception e) {
		}
	}

	public static FastCraft get() {
		return fastCraft;
	}

	public int getVersion() {
		return version;
	}

	public InventoryManager getInventoryManager() {
		return inventoryManager;
	}

	public PluginUpdater getPluginUpdater() {
		return pluginUpdater;
	}

	public HashMap<Material, Achievement> getAchievements() {
		return achievements;
	}

	public static void info(String msg) {
		fastCraft.getLogger().info(ChatColor.stripColor(msg));
	}

	public static void error(Throwable error) {
		fastCraft.getLogger().log(Level.SEVERE, null, error);
	}

	public static ConfigManager configs() {
		return get().configManager;
	}
}
