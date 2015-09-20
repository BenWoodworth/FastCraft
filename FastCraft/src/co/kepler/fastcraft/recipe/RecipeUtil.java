package co.kepler.fastcraft.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.config.PluginConfig;

/**
 * A utility class to help manage FastCraft recipes.
 * 
 * @author Kepler_
 */
public abstract class RecipeUtil {
	private static RecipeUtil instance;
	protected Set<String> badHashes;
	private List<FastRecipe> recipes;

	public static RecipeUtil setup() {
		instance = null;

		String className = Bukkit.getServer().getClass().getPackage().getName();
		String version = className.substring(className.lastIndexOf('.') + 1);
		try {
			Class<?> c = Class.forName(
					"co.kepler.fastcraft.recipe.RecipeUtil_" + version);
			instance = (RecipeUtil) c.newInstance();
		} catch (Exception e) {
			FastCraft.info("May not be compatible with this version of Minecraft! (" + version + ")");
			instance = new RecipeUtil_v1_8_R3();
		}
		instance.loadRecipes();
		return instance;
	}

	private void loadRecipes() {
		if (recipes == null) {
			recipes = new ArrayList<FastRecipe>();
		} else {
			recipes.clear();
		}
		Iterator<Recipe> iter = Bukkit.recipeIterator();
		PluginConfig config = FastCraft.configs().config;
		while (iter.hasNext()) {
			Recipe cur = iter.next();
			if (!FastRecipe.canBeFastRecipe(cur)) {
				continue;
			}
			FastRecipe fr = new FastRecipe(cur);
			String hash = fr.getHash();
			if (badHashes.contains(hash) ||
					config.isHashDisabled(hash) ||
					config.isResultDisabled(fr.getResult().getData())) {
				continue;
			}
			boolean disabled = false;
			for (Ingredient i : fr.getIngredients().getList()) {
				if (config.isIngredientDisabled(i.getMaterialData())) {
					disabled = true;
					break;
				}
			}
			if (!disabled) {
				recipes.add(fr);
			}
		}
	}

	public static RecipeUtil getInstance() {
		if (instance == null) {
			return setup();
		}
		return instance;
	}

	public List<FastRecipe> getRecipes() {
		loadRecipes(); // TODO Make efficient? (Only load when recipes change)
		return recipes;
	}
	
	public abstract String getItemName(ItemStack is);
}
