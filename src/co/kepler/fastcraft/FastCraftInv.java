package co.kepler.fastcraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.kepler.fastcraft.config.PluginConfig;
import co.kepler.fastcraft.recipe.FastRecipe;
import co.kepler.fastcraft.recipe.Ingredient;
import co.kepler.fastcraft.recipe.IngredientList;
import co.kepler.fastcraft.recipe.RecipeUtil;

/**
 * Object to represent FastCraft interfaces.
 * 
 * @author Kepler_
 */
public class FastCraftInv {
	private IngredientList ingredients;
	private List<FastRecipe> craftableItems;
	private int page;
	private int craftAmount;
	private Inventory curInv;

	public FastCraftInv() {
		ingredients = new IngredientList();
		craftableItems = new ArrayList<FastRecipe>();
		page = 0;
		craftAmount = 0;
		curInv = null;
	}

	public void setPage(int newPage) {
		page = Math.max(0, Math.min(newPage, getLastPage()));
	}

	public int getPage() {
		return page;
	}
	
	public int getCraftAmount() {
		return craftAmount;
	}
	
	public void decreaseCraftAmount() {
		int increment = FastCraft.configs().config.craftAmount_increment();
		int maximum = FastCraft.configs().config.craftAmount_maximum();
		if (craftAmount == 0) {
			craftAmount = maximum;
		} else {
			craftAmount = Math.max(0, craftAmount - increment);
		}
		if (craftAmount == 1) craftAmount = 0;
	}
	
	public void increaseCraftAmount() {
		int increment = FastCraft.configs().config.craftAmount_increment();
		int maximum = FastCraft.configs().config.craftAmount_maximum();
		if (craftAmount == maximum) {
			craftAmount = 0;
		} else {
			craftAmount = Math.min(maximum, craftAmount + increment);
		}
		if (craftAmount == 1) craftAmount = 2;
	}

	public int getLastPage() {
		int lastItem = craftableItems.size() - 1;
		while (lastItem > 0 && !craftableItems.get(lastItem).canCraft(ingredients)) {
			lastItem--;
		}
		return (int) Math.ceil(Math.max(0, lastItem) /
				(InventoryManager.NUM_CRAFTING_ROWS * 9));
	}

	public List<FastRecipe> getCraftableItems() {
		return craftableItems;
	}

	public void setIngredients(IngredientList i) {
		ingredients = i;
	}

	public IngredientList getIngredients() {
		return ingredients;
	}

	private void updateCraftableItems() {
		List<FastRecipe> newRecipes = new ArrayList<FastRecipe>();
		for (FastRecipe fr : RecipeUtil.getInstance().getRecipes()) {
			if (!craftableItems.contains(fr) && fr.canCraft(ingredients)) {
				newRecipes.add(fr);
			}
		}
		Collections.sort(newRecipes, new Comparator<FastRecipe>() {
			@Override
			public int compare(FastRecipe is0, FastRecipe is1) {
				return is0.getResult().getType().compareTo(is1.getResult().getType());
			}
		});
		craftableItems.addAll(newRecipes);
	}

	public void resetCraftableItems() {
		craftableItems.clear();
		updateCraftableItems();
	}

	public void updateInv(InventoryHolder holder) {
		Inventory inv;
		int rows = InventoryManager.NUM_CRAFTING_ROWS * 9;
		inv = Bukkit.createInventory(holder, 54,
				FastCraft.configs().lang.invTitle());
		updateCraftableItems();
		PluginConfig config = FastCraft.configs().config;
		if (page > 0) {
			inv.setItem(InventoryManager.BUTTON_PREV_SLOT,
					config.getInvButtonPrevItem(page, getLastPage() + 1));
		}
		if (page < getLastPage()) {
			inv.setItem(InventoryManager.BUTTON_NEXT_SLOT,
					config.getInvButtonNextItem(page + 2, getLastPage() + 1));
		}
		inv.setItem(InventoryManager.BUTTON_AMOUNT_SLOT, config.getInvButtonAmountItem(craftAmount));
		inv.setItem(InventoryManager.BUTTON_CRAFT_SLOT, config.getInvButtonCraftItem());
		inv.setItem(InventoryManager.BUTTON_REFRESH_SLOT, config.getInvButtonRefreshItem());
		int startIndex = getPage() * rows;
		for (int i = startIndex; i < Math.min(startIndex + rows, craftableItems.size()); i++) {
			FastRecipe curRecipe = craftableItems.get(i);
			if (curRecipe.canCraft(ingredients)) {
				ItemStack item = new ItemStack(curRecipe.getResult());
				ItemMeta im = item.getItemMeta();

				List<String> lore = new ArrayList<String>();
				for (Ingredient curIng : curRecipe.getIngredients().getList()) {
					// TODO Throwing error:
					lore.add(FastCraft.configs().lang.ingredientsFormat(curIng.getAmount(), curIng.getName()));
				}
				if (config.debug_showRecipeHashes()) {
					lore.add("§8Hash: " + curRecipe.getHash());
				}
				if (im.getLore() == null) {
					im.setLore(lore);
				} else {
					im.getLore().add(" ");
					im.getLore().addAll(lore);
				}
				item.setItemMeta(im);
				inv.setItem(i - startIndex, item);
			}
		}
		curInv = inv;
	}

	public Inventory getCurrentInv() {
		return curInv;
	}
}
