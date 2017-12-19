package co.kepler.fastcraft;

import co.kepler.fastcraft.api.gui.GUI;
import co.kepler.fastcraft.bstats.MetricsLite;
import co.kepler.fastcraft.commands.CommandManager;
import co.kepler.fastcraft.compat.RecipeCompatManager;
import co.kepler.fastcraft.config.*;
import co.kepler.fastcraft.craftgui.GUIFastCraft;
import co.kepler.fastcraft.craftgui.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for FastCraft
 */
public class FastCraft extends JavaPlugin {
    private static FastCraft instance;

    private List<ConfigExternal> externalConfigs;
    private PluginConfig config;
    private LanguageConfig lang;
    private BlacklistConfig blacklist;

    private RecipeCompatManager recipeCompatManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;

        // Relocate FastCraftPlus/ directory to FastCraft/
        relocate();

        // Create and load configurations
        externalConfigs = new ArrayList<>();
        externalConfigs.add(config = new PluginConfig());
        externalConfigs.add(lang = new LanguageConfig());
        externalConfigs.add(blacklist = new BlacklistConfig());
        load();

        // Load managers
        recipeCompatManager = new RecipeCompatManager();
        playerManager = new PlayerManager();

        // Register events
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GUIFastCraft.GUIListener(), this);
        pluginManager.registerEvents(playerManager, this);

        // Register commands
        new CommandManager().registerCommands();

        // Load metrics
        new MetricsLite(this);
    }

    @Override
    public void onDisable() {
        unload();
    }

    /**
     * Load FastCraft.
     */
    public static void load() {
        for (ConfigExternal conf : getInstance().externalConfigs) conf.load();
    }

    /**
     * Unload FastCraft.
     */
    public static void unload() {
        GUI.disposeAll();
        PlayerManager.Prefs.saveAllPrefs();
    }

    /**
     * Reload FastCraft.
     */
    public void reload() {
        unload();
        load();
    }

    /**
     * Get an instance of FastCraft.
     *
     * @return Returns an instance of FastCraft.
     */
    public static FastCraft getInstance() {
        return instance;
    }

    /**
     * Get the plugin's configuration.
     *
     * @return Returns the configuration.
     */
    public static PluginConfig config() {
        return instance.config;
    }

    /**
     * Get the plugin's language configuration.
     *
     * @return Returns the language configuration.
     */
    public static LanguageConfig lang() {
        return instance.lang;
    }

    /**
     * Get the plugin's blacklist configuration.
     *
     * @return Returns the blacklist configuration.
     */
    public static BlacklistConfig blacklist() {
        return instance.blacklist;
    }

    /**
     * Get the plugin's compatibility manager.
     *
     * @return Returns the compatibility manager.
     */
    public static RecipeCompatManager recipeManager() {
        return instance.recipeCompatManager;
    }

    /**
     * Get an instance of the player manager.
     *
     * @return Returns an instance of the player manager.
     */
    public static PlayerManager playerManager() {
        return instance.playerManager;
    }

    /**
     * Log an error message to the console.
     *
     * @param msg The message to log.
     */
    public static void err(String msg) {
        instance.getLogger().severe(ChatColor.RED + msg);
    }

    public static void debug(String msg) {
        if (!config().debug()) return;
        instance.getLogger().info("[Debug] " + msg);
    }

    /**
     * Log info to the console.
     *
     * @param msg The message to log.
     */
    public static void log(String msg) {
        instance.getLogger().info(msg);
    }

    /**
     * Log a warning message to the console.
     *
     * @param msg The message to log.
     */
    public static void warning(String msg) {
        instance.getLogger().warning(msg);
    }

    /**
     * Get the FastCraft jar file.
     *
     * @return Returns the FastCraft jar file.
     */
    public static File getJarFile() {
        return instance.getFile();
    }

    /**
     * Relocate the plugin directory.
     */
    private void relocate() {
        File fc = new File("plugins/FastCraft");
        File fcp = new File("plugins/FastCraftPlus/");

        if (fcp.exists() && !fc.exists()) {
            getLogger().info("Renaming 'plugins/FastCraftPlus' to 'plugins/FastCraft'");
            fcp.renameTo(fc);
        }

        // Deprecate recipes file
        File r = new File("plugins/FastCraft/recipes.yml");
        File r2 = new File("plugins/FastCraft/UNSUPPORTED-recipes.yml");
        if (r.exists()) {
            getLogger().info("FastCraft no longer supports custom recipes");
            getLogger().info("Renaming 'recipes.yml' to 'UNSUPPORTED-recipes.yml'");
            r.renameTo(r2);
        }
    }
}
