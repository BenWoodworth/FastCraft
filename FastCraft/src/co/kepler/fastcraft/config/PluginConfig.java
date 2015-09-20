package co.kepler.fastcraft.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.Util;

/**
 * Manages config.yml
 * 
 * @author Kepler_
 */
public class PluginConfig {
	private final String resource = "config.yml";
	private final YamlConfiguration config, def;
	private final File configFile;

	@SuppressWarnings("deprecation")
	public PluginConfig() {
		FastCraft fastCraft = FastCraft.get();

		configFile = new File(fastCraft.getDataFolder(), resource);

		if (!configFile.exists()) {
			FastCraft.get().saveResource(resource, false);
		}

		config = YamlConfiguration.loadConfiguration(configFile);
		InputStream defaultStream = fastCraft.getResource(resource);
		if (defaultStream != null) {
			def = YamlConfiguration.loadConfiguration(defaultStream);
			config.setDefaults(def);
		} else {
			def = null;
		}
		save();
	}

	public void load() {
		prevItem = nextItem = amountItem = craftItem = refreshItem = null;
		disabledIngredients = disabledResults = null;
		commandCompatibility = null;
		disabledHashes = null;
		try {
			Util.loadYaml(config, configFile);
		} catch (FileNotFoundException e) {
			FastCraft.error(e);
		}
	}

	public void save() {
		config.options().copyDefaults(true);
		config.options().copyHeader(true);
		if (def != null) {
			config.options().header(def.options().header());
			for (String key : config.getKeys(true)) {
				if (!def.contains(key)) {
					config.set(key, null);
				}
			}
		}
		try {
			config.save(configFile);
		} catch (IOException e) {
			FastCraft.error(e);
		}
	}

	public boolean fastCraftDefaultEnabled()
	{ return config.getBoolean("fastCraftDefaultEnabled"); }

	public boolean removeItemsInCreative()
	{ return config.getBoolean("removeItemsInCreative"); }
	
	public int craftAmount_increment()
	{ return config.getInt("craftAmount.increment"); }
	public int craftAmount_maximum()
	{ return config.getInt("craftAmount.maximum"); }
	
	private String interfaceItems_prevPage()
	{ return config.getString("interfaceItems.prevPage"); }
	private String interfaceItems_nextPage()
	{ return config.getString("interfaceItems.nextPage"); }
	private String interfaceItems_amount()
	{ return config.getString("interfaceItems.amount"); }
	private String interfaceItems_craft()
	{ return config.getString("interfaceItems.craft"); }
	private String interfaceItems_refresh()
	{ return config.getString("interfaceItems.refresh"); }

	private List<String> recipes_disabledIngredients()
	{ return config.getStringList("recipes.disabledIngredients"); }
	private List<String> recipes_disabledResults()
	{ return config.getStringList("recipes.disabledResults"); }
	private List<String> recipes_disabledHashes()
	{ return config.getStringList("recipes.disabledHashes"); }
	
	private List<String> commandCompatibility()
	{ return config.getStringList("commandCompatibility"); }

	public boolean autoUpdate_enabled()
	{ return config.getBoolean("autoUpdate.enabled"); }
	public boolean autoUpdate_devBuilds()
	{ return config.getBoolean("autoUpdate.devBuilds"); }
	public boolean autoUpdate_autoDownload()
	{ return config.getBoolean("autoUpdate.autoDownload"); }
	public int autoUpdate_checkInterval()
	{ return config.getInt("autoUpdate.checkInterval"); }
	public boolean autoUpdate_showConsoleMessages()
	{ return config.getBoolean("autoUpdate.showConsoleMessages"); }

	public boolean debug_showRecipeHashes()
	{ return config.getBoolean("debug.showRecipeHashes"); }

	public String language()
	{ return config.getString("language"); }



	@SuppressWarnings("deprecation")
	public static MaterialData parseItem(String item) {
		String[] split = item.split(":");
		String material = "0";
		String data = "-1";
		switch (split.length) {
		case 2: data = split[1];
		case 1: material = split[0];
		}
		return new MaterialData(Integer.parseInt(material), Byte.parseByte(data));
	}

	private ItemStack prevItem;
	public ItemStack getInvButtonPrevItem(int prevPage, int totalPages) {
		if (prevItem == null) {
			prevItem = parseItem(interfaceItems_prevPage()).toItemStack();
			prevItem.setAmount(1);
		}
		ItemStack result = prevItem.clone();
		ItemMeta im = result.getItemMeta();
		LanguageConfig lang = FastCraft.configs().lang;
		im.setDisplayName(lang.buttonPrevName(prevPage, totalPages));
		im.setLore(lang.buttonPrevLore(prevPage, totalPages));
		result.setItemMeta(im);
		return result;
	}

	private ItemStack nextItem;
	public ItemStack getInvButtonNextItem(int nextPage, int totalPages) {
		if (nextItem == null) {
			nextItem = parseItem(interfaceItems_nextPage()).toItemStack();
			nextItem.setAmount(1);
		}
		ItemStack result = nextItem.clone();
		ItemMeta im = result.getItemMeta();
		LanguageConfig lang = FastCraft.configs().lang;
		im.setDisplayName(lang.buttonNextName(nextPage, totalPages));
		im.setLore(lang.buttonNextLore(nextPage, totalPages));
		result.setItemMeta(im);
		return result;
	}

	private ItemStack amountItem;
	public ItemStack getInvButtonAmountItem(int amount) {
		if (amountItem == null) {
			amountItem = parseItem(interfaceItems_amount()).toItemStack();
			ItemMeta im = amountItem.getItemMeta();
			LanguageConfig lang = FastCraft.configs().lang;
			im.setDisplayName(lang.buttonAmountName());
			amountItem.setItemMeta(im);
			amountItem.setAmount(1);
		}
		ItemStack result = amountItem.clone();
		if (amount != 0) {
			result.setAmount(amount);
			ItemMeta im = result.getItemMeta();
			im.setLore(FastCraft.configs().lang.buttonAmountLore(amount));
			result.setItemMeta(im);
		}
		return result;
	}

	private ItemStack craftItem;
	public ItemStack getInvButtonCraftItem() {
		if (craftItem == null) {
			craftItem = parseItem(interfaceItems_craft()).toItemStack();
			ItemMeta im = craftItem.getItemMeta();
			LanguageConfig lang = FastCraft.configs().lang;
			im.setDisplayName(lang.buttonCraftName());
			im.setLore(lang.buttonCraftLore());
			craftItem.setItemMeta(im);
			craftItem.setAmount(1);
		}
		return craftItem;
	}

	private ItemStack refreshItem;
	public ItemStack getInvButtonRefreshItem() {
		if (refreshItem == null) {
			refreshItem = parseItem(interfaceItems_refresh()).toItemStack();
			ItemMeta im = refreshItem.getItemMeta();
			LanguageConfig lang = FastCraft.configs().lang;
			im.setDisplayName(lang.buttonRefreshName());
			im.setLore(lang.buttonRefreshLore());
			refreshItem.setItemMeta(im);
			refreshItem.setAmount(1);
		}
		return refreshItem;
	}
	
	private List<String> commandCompatibility;
	public boolean isCommandCompatible(String command) {
		if (commandCompatibility == null) {
			commandCompatibility = new ArrayList<String>();
			for (String s : commandCompatibility()) {
				commandCompatibility.add(s.toLowerCase());
			}
		}
		command = command.toLowerCase();
		for (String s : commandCompatibility) {
			if (command.startsWith(s)) {
				return false;
			}
		}
		return true;
	}
	
	private Set<MaterialData> disabledIngredients;
	public boolean isIngredientDisabled(MaterialData m) {
		if (disabledIngredients == null) {
			disabledIngredients = new HashSet<MaterialData>();
			for (String s : recipes_disabledIngredients()) {
				disabledIngredients.add(parseItem(s));
			}
		}
		return disabledIngredients.contains(m);
	}
	
	private Set<MaterialData> disabledResults;
	public boolean isResultDisabled(MaterialData m) {
		if (disabledResults == null) {
			disabledResults = new HashSet<MaterialData>();
			for (String s : recipes_disabledResults()) {
				disabledResults.add(parseItem(s));
			}
		}
		return disabledResults.contains(m);
	}
	
	private Set<String> disabledHashes;
	public boolean isHashDisabled(String hash) {
		if (disabledHashes == null) {
			disabledHashes = new HashSet<String>();
			for (String s : recipes_disabledHashes()) {
				disabledHashes.add(s.toUpperCase());
			}
		}
		return disabledHashes.contains(hash.toUpperCase());
	}
}
