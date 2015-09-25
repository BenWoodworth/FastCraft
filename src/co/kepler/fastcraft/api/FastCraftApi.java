package co.kepler.fastcraft.api;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import co.kepler.fastcraft.FastCraft;
import co.kepler.fastcraft.FastCraftInv;
import co.kepler.fastcraft.config.PlayersConfig;
import co.kepler.fastcraft.recipe.FastRecipe;

/**
 * API to allow other plugins to interact with FastCraft.
 * 
 * @author Kepler_
 */
public class FastCraftApi {
	
	/**
	 * Check if an inventory is a FastCraft interface.
	 * @param inventory Inventory to check.
	 * @return Returns true if the inventory is a FastCraft interface.
	 */
	public static boolean isFastCraftInventory(Inventory inventory) {
		for (FastCraftInv i : FastCraft.get().getInventoryManager().getInventories().values()) {
			Inventory check = i.getCurrentInv();
			if (!inventory.getHolder().equals(check.getHolder()) ||
					!inventory.getName().equals(check.getName()) ||
					inventory.getSize() != check.getSize() ||
					!inventory.getTitle().equals(check.getTitle()) ||
					!inventory.getType().equals(check.getType()) ||
					inventory.getSize() != check.getSize() ||
					inventory.getViewers().size() != check.getViewers().size()) {
				continue;
			}
			boolean equal = true;
			for (int index = 0; index < inventory.getSize() && equal; index++) {
				ItemStack a = inventory.getItem(index);
				ItemStack b = check.getItem(index);
				if (!((a == null && b == null) || a.equals(b))) {
					equal = false;
				}
			}
			for (int index = 0; index < inventory.getViewers().size() && equal; index++) {
				HumanEntity a = inventory.getViewers().get(index);
				HumanEntity b = check.getViewers().get(index);
				if (!((a == null && b == null) || a.equals(b))) {
					equal = false;
				}
			}
			if (!equal) {
				continue;
			}
			return true;
		}
		return false;
	}

	/**
	 * Compare two inventories to check if they're equal.
	 * @param inv0 First inventory to compare.
	 * @param inv1 Second inventory to compare.
	 * @return Returns true if the inventories are equal.
	 */
	public static boolean compareInvs(Inventory inv0, Inventory inv1) {
		if (!inv0.getHolder().equals(inv1.getHolder()) ||
				!inv0.getName().equals(inv1.getName()) ||
				inv0.getSize() != inv1.getSize() ||
				!inv0.getTitle().equals(inv1.getTitle()) ||
				!inv0.getType().equals(inv1.getType()) ||
				inv0.getSize() != inv1.getSize() ||
				inv0.getViewers().size() != inv1.getViewers().size()) {
			return false;
		}
		for (int index = 0; index < inv0.getSize(); index++) {
			ItemStack a = inv0.getItem(index);
			ItemStack b = inv1.getItem(index);
			if (!((a == null && b == null) || a.equals(b))) {
				return false;
			}
		}
		for (int index = 0; index < inv0.getViewers().size(); index++) {
			HumanEntity a = inv0.getViewers().get(index);
			HumanEntity b = inv1.getViewers().get(index);
			if (!((a == null && b == null) || a.equals(b))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get a FastRecipe from a Recipe.
	 * @param recipe The Recipe to make a FastRecipe from.
	 * @return Returns a new FastRecipe.
	 */
	public static FastRecipe getFastRecipe(Recipe recipe) {
		return new FastRecipe(recipe);
	}

	/**
	 * Get the HashCode for a recipe.
	 * 
	 * @param recipe The recipe to get the HashCode of.
	 * @return Returns the recipe's HashCode.
	 */
	public static String getRecipeHash(Recipe recipe) {
		return new FastRecipe(recipe).getHash();
	}
	
	/**
	 * Run this method before opening a crafting inventory. The next time
	 * a crafting inventory opens, FastCraft's interface won't open.
	 * 
	 * @param players The players who will have a crafting inventory opened.
	 */
	public static void allowCraftingInvToOpen(Player... players) {
		PlayersConfig pc = FastCraft.configs().players;
		for (Player p : players) {
			String id = pc.getID(p);
			FastCraft.get().getInventoryManager().tempDisable(id);
		}
	}
}
