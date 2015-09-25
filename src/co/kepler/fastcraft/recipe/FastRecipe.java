package co.kepler.fastcraft.recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import co.kepler.fastcraft.MyCraftingInventory;

/**
 * Represents a recipe that FastCraft can use.
 * 
 * @author Kepler_
 */
public class FastRecipe {
	private IngredientList ingredients;
	private ItemStack result;
	private Recipe recipe;

	public FastRecipe(Recipe recipe) {
		this.recipe = recipe;
		ingredients = new IngredientList();
		if (recipe instanceof ShapedRecipe) {
			ShapedRecipe cur = (ShapedRecipe) recipe;
			for (ItemStack is : cur.getIngredientMap().values()) {
				if (is != null && is.getType() != Material.AIR) {
					ingredients.add(new Ingredient(is, 1));
				}
			}
			result = recipe.getResult();
		} else if (recipe instanceof ShapelessRecipe) {
			ShapelessRecipe cur = (ShapelessRecipe) recipe;
			for (ItemStack i : cur.getIngredientList()) {
				ingredients.add(new Ingredient(i, 1));
			}
			result = recipe.getResult();
		}
	}

	public FastRecipe(IngredientList ingredients, ItemStack result) {
		this.ingredients = ingredients;
		this.result = result;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public IngredientList getIngredients() {
		return ingredients;
	}

	public boolean canCraft(IngredientList withItems) {
		return withItems.containsAll(ingredients);
	}

	public ItemStack getResult() {
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FastRecipe)) {
			return false;
		}
		FastRecipe test = (FastRecipe) obj;
		if (!getIngredients().equals(test.getIngredients())) {
			return false;
		}
		if (!getResult().equals(test.getResult())) {
			return false;
		}
		return true;
	}

	public CraftingInventory createCraftingInventory(HumanEntity p) {
		final Inventory inv = Bukkit.createInventory(p, 18);
		if (recipe instanceof ShapedRecipe) {
			ShapedRecipe r = (ShapedRecipe) recipe;
			for (int row = 0; row < 3 && row < r.getShape().length; row++) {
				for (int col = 0; col < 3 && col < r.getShape()[row].length(); col++) {
					inv.setItem(row * 3 + col + 1, r.getIngredientMap().get(r.getShape()[row].charAt(col)));
				}
			}
		} else if (recipe instanceof ShapelessRecipe) {
			ShapelessRecipe r = (ShapelessRecipe) recipe;
			int curSlot = 1;
			for (ItemStack ing : r.getIngredientList()) {
				if (curSlot > 10) {
					break;
				}
				inv.setItem(curSlot++, ing);
			}
		}
		inv.setItem(0, recipe.getResult());
		return new MyCraftingInventory(inv, recipe);
	}

	private String hash = null;

	@SuppressWarnings("deprecation")
	public String getHash() {
		if (hash != null) {
			return hash;
		}
		int hashInt = 7;
		List<Ingredient> il = ingredients.getList();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= ingredients.getList().size(); i++) {
			ItemStack cur;
			if (i == il.size()) {
				cur = result;
			} else {
				cur = il.get(i).getItem();
			}
			if (cur == null) {
				sb.append("null!");
			} else {
				sb.append(cur.getData().getItemType())
				.append('$')
				.append(cur.getData().getData())
				.append('#')
				.append(cur.getAmount())
				.append('*')
				.append(cur.getItemMeta())
				.append('!');
			}
		}
		for (char c : sb.toString().toCharArray()) {
			hashInt = (hashInt * 127 + c);
		}
		return hash = String.format("%08x", hashInt).toUpperCase();
	}

	public boolean hashEquals(String test) {
		return getHash().equalsIgnoreCase(test);
	}
	
	public static boolean canBeFastRecipe(Recipe r) {
		return r instanceof ShapedRecipe || r instanceof ShapelessRecipe;
	}
	
	public int getMaxCraftAmount(Inventory inv) {
		// Make a list of items in the inventory
		List<Ingredient> available = new ArrayList<Ingredient>();
		for (ItemStack is : inv) {
			if (is == null) continue;
			boolean added = false;
			for (Ingredient i : available) {
				if (i.isSimilar(is)) {
					i.setAmount(i.getAmount() + is.getAmount());
					added = true;
					break;
				}
			}
			if (!added) {
				available.add(new Ingredient(is));
			}
		}
		
		// See how many times this recipe can be crafted
		int result = 0;
		boolean done = false;
		while (true) {
			for (Ingredient i : ingredients.getList()) {
				if (i.hasDataWildcard()) continue;
				done = !removeFromAvailable(available, i);
			}
			if (done) break;
			for (Ingredient i : ingredients.getList()) {
				if (!i.hasDataWildcard()) continue;
				done = !removeFromAvailable(available, i);
			}
			if (done) break;
			result++;
		}
		return result;
	}
	
	private boolean removeFromAvailable(List<Ingredient> available, Ingredient ing) {
		int toRemove = ing.getAmount();
		for (Ingredient removeFrom : available) {
			if (!removeFrom.isSimilar(ing)) continue;
			if (removeFrom.getAmount() <= toRemove) {
				toRemove -= removeFrom.getAmount();
				removeFrom.setAmount(0);
			} else {
				removeFrom.setAmount(removeFrom.getAmount() - toRemove);
				toRemove = 0;
			}
		}
		return toRemove == 0;
	}
}
