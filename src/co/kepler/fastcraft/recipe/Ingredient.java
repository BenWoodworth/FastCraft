package co.kepler.fastcraft.recipe;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import co.kepler.fastcraft.FastCraft;

/**
 * An ingredient to a recipe.
 * 
 * @author Kepler_
 */
public class Ingredient {
	private ItemStack item;

	public Ingredient(ItemStack item, int amount) {
		this.item = item.clone();
		this.item.setAmount(amount);
	}

	public Ingredient(ItemStack item) {
		this(item, item.getAmount());
	}

	public Ingredient(Ingredient ingredient, int amount) {
		this(ingredient.item, amount);
	}

	public Ingredient(Ingredient ingredient) {
		this(ingredient.item, ingredient.getAmount());
	}

	@SuppressWarnings("deprecation")
	public boolean hasDataWildcard() {
		return item.getData().getData() == -1;
	}

	public ItemStack getItem() {
		return item.clone();
	}
	
	public MaterialData getMaterialData() {
		return item.getData();
	}

	public int getAmount() {
		return item.getAmount();
	}

	public void setAmount(int amount) {
		item.setAmount(amount);
	}
	
	public boolean isSimilar(Ingredient i) {
		// If neither data are wildcards, and the data aren't equal;
		if (!hasDataWildcard() && !i.hasDataWildcard()) {
			if (item.getDurability() != i.item.getDurability()) return false;
		}
		
		// Check if item types are equal
		if (item.getType() != i.item.getType()) return false;
		
		// Compare metadata
		if (!item.getItemMeta().equals(i.item.getItemMeta())) return false;
		
		// Return true if passed all previous tests
		return true;
	}
	
	public boolean isSimilar(ItemStack is) {
		// If neither data are wildcards, and the data aren't equal;
		if (!hasDataWildcard()) {
			if (item.getDurability() != is.getDurability()) return false;
		}
		
		// Check if item types are equal
		if (item.getType() != is.getType()) return false;
		
		// Compare metadata
		if (!item.getItemMeta().equals(is.getItemMeta())) return false;
		
		// Return true if passed all previous tests
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Ingredient)) return false;
		
		// Compare amounts
		Ingredient i = (Ingredient) o;
		if (item.getAmount() != i.item.getAmount()) return false;
		
		// Return true if is similar
		return isSimilar(i);
	}

	public String getName() {
		return FastCraft.get().recipeUtil.getItemName(item);
	}
}
