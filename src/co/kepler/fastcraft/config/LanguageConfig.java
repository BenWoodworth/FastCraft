package co.kepler.fastcraft.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.Util;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;

/**
 * Manages FastCraft language.
 * 
 * @author Kepler_
 */
public class LanguageConfig {
	private final YamlConfiguration defaults, config;
	private final File langFile;

	public LanguageConfig() {
		defaults = new YamlConfiguration();
		config = new YamlConfiguration();
		langFile = new File(FastCraft.get().getDataFolder(), "language.yml");
	}

	public void load(String lang) throws IOException, InvalidConfigurationException {
		// Load default language entries from resources
		String resource = "languages/" + lang.toUpperCase() + ".yml";
		InputStream stream = FastCraft.get().getResource(resource);
		if (stream != null) {
			Util.loadYaml(config, stream);
		} else {
			FastCraft.info("Invalid language: " + lang.toUpperCase());
		}
		
		// Load language config file, and set defaults
		if (!langFile.exists()) {
			FastCraft.get().saveResource("language.yml", false);
		}
		Util.loadYaml(config, langFile);
		config.setDefaults(defaults);
	}

	private String getLang(String key, String... vars) {
		String lang = config.getString(key);
		if (lang == null) {
			lang = "[error-langMissing: " + key + "]";
		} else {
			for (int x = 0; x < vars.length; x++) {
				lang = lang.replace("%%" + x, vars[x]);
			}
			lang = lang.replace("&", "§").replace("%%n", "\n");
		}
		return lang;
	}

	public String i(int i) {
		return Integer.toString(i);
	}

	public List<String> s(String str) {
		return Arrays.asList(str.split("\n"));
	}

	public String errNoPerm(FcPerm permission)
	{ return getLang("errNoPerm", permission.value); }
	public String errCmdUsage(String usage)
	{ return getLang("errCmdUsage", usage); }
	public String errCmdPlayerOnly()
	{ return getLang("errCmdPlayerOnly"); }
	public String errPlayerNotFound(String player)
	{ return getLang("errPlayerNotFound", player); }

	public String updateNotification(String name)
	{ return getLang("updateNotification", name); }

	public String outFcToggleOn()
	{ return getLang("outFcToggleOn"); }
	public String outFcToggleOff()
	{ return getLang("outFcToggleOff"); }
	public String outFcToggleOther(String player)
	{ return getLang("outFcToggleOther", player); }
	public String outFcToggleOnOther(String player)
	{ return getLang("outFcToggleOnOther", player); }
	public String outFcToggleOffOther(String player)
	{ return getLang("outFcToggleOffOther", player); }

	public String outFcAdminReload()
	{ return getLang("outFcAdminReload"); }

	public String outFcAdminUpdate_Checking()
	{ return getLang("outFcAdminUpdate-Checking"); }
	public String outFcAdminUpdate_ErrFetch()
	{ return getLang("outFcAdminUpdate-ErrFetch"); }
	public String outFcAdminUpdate_None()
	{ return getLang("outFcAdminUpdate-None"); }
	public String outFcAdminUpdate_Available(String version)
	{ return getLang("outFcAdminUpdate-Available", version); }
	public String outFcAdminUpdate_Downloading()
	{ return getLang("outFcAdminUpdate-Downloading"); }
	public String outFcAdminUpdate_DlErr(String version)
	{ return getLang("outFcAdminUpdate-DlErr", version); }
	public String outFcAdminUpdate_Complete(String version)
	{ return getLang("outFcAdminUpdate-Complete", version); }
	public String outFcAdminUpdate_Downloaded(String version)
	{ return getLang("outFcAdminUpdate-Downloaded", version); }

	public String invTitle()
	{ return getLang("invTitle"); }

	public String buttonPrevName(int prevPage, int totalPages)
	{ return getLang("buttonPrevName", i(prevPage), i(totalPages)); }
	public List<String> buttonPrevLore(int prevPage, int totalPages)
	{ return s(getLang("buttonPrevLore", i(prevPage), i(totalPages))); }
	public String buttonNextName(int nextPage, int totalPages)
	{ return getLang("buttonNextName", i(nextPage), i(totalPages)); }
	public List<String> buttonNextLore(int nextPage, int totalPages)
	{ return s(getLang("buttonNextLore", i(nextPage), i(totalPages))); }
	public String buttonAmountName() // TODO Update in language files, and add Italian
	{ return getLang("buttonAmountName"); }
	public List<String> buttonAmountLore(int amount)
	{ return s(getLang("buttonAmountLore", i(amount))); }
	public String buttonCraftName()
	{ return getLang("buttonCraftName"); }
	public List<String> buttonCraftLore()
	{ return s(getLang("buttonCraftLore")); }
	public String buttonRefreshName()
	{ return getLang("buttonRefreshName"); }
	public List<String> buttonRefreshLore()
	{ return s(getLang("buttonRefreshLore")); }

	public String ingredientsFormat(int quantity, String item)
	{ return getLang("ingredientsFormat", i(quantity), item); }
}
