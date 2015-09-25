package co.kepler.fastcraft.recipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * A list of ingredients for a recipe.
 * 
 * @author Kepler_
 */
public class IngredientList {
	private List<Ingredient> ingredients;

	public IngredientList() {
		ingredients = new ArrayList<Ingredient>();
	}

	public IngredientList(ItemStack... i) {
		this();
		add(i);
	}
	
	public IngredientList(Player p) {
		this(p.getInventory().getContents());
	}

	public IngredientList(IngredientList i) {
		ingredients = new ArrayList<Ingredient>();
		for (Ingredient ing : i.getList()) {
			ingredients.add(new Ingredient(ing));
		}
	}

	public List<Ingredient> getList() {
		return ingredients;
	}

	public void clear() {
		ingredients.clear();
	}

	public void add(Ingredient i) {
		for (Ingredient ing : ingredients) {
			if (i.isSimilar(ing)) {
				ing.setAmount(ing.getAmount() + i.getAmount());
				return;
			}
		}
		ingredients.add(i);
	}

	public void add(Ingredient i, int amount) {
		for (Ingredient ing : ingredients) {
			if (i.isSimilar(ing)) {
				ing.setAmount(ing.getAmount() + amount);
				return;
			}
		}
		ingredients.add(i);
	}

	public void add(ItemStack... i) {
		for (ItemStack is : i) {
			if (is != null) {
				add(new Ingredient(is, is.getAmount()));
			}
		}
	}

	public void remove(Ingredient i) {
		for (Ingredient ing : ingredients) {
			if (i.isSimilar(ing)) {
				ingredients.remove(ing);
				return;
			}
		}
	}

	public void remove(Ingredient i, int amount) {
		for (Ingredient ing : ingredients) {
			if (i.isSimilar(ing)) {
				ing.setAmount(ing.getAmount() - amount);
				if (ing.getAmount() <= 0) {
					ingredients.remove(ing);
				}
				return;
			}
		}
	}

	// returns true if succeeded
	public boolean removeAll(Ingredient... i) {
		List<Ingredient> remaining = new ArrayList<Ingredient>();
		for (Ingredient ing : i) {
			remaining.add(new Ingredient(ing));
		}

		for (Ingredient removeFrom : remaining) {
			for (int wild = 0; wild <= 1; wild++) {
				for (Ingredient curRemove : ingredients) {
					boolean remove = false;
					if (wild == 0) {
						if (removeFrom.isSimilar(curRemove) && !removeFrom.hasDataWildcard()) {
							remove = true;
						}
					} else {
						if (removeFrom.isSimilar(curRemove)) {
							remove = true;
						}
					}
					if (remove) {
						int removeAmount = Math.min(curRemove.getAmount(), removeFrom.getAmount());
						removeFrom.setAmount(removeFrom.getAmount() - removeAmount);
						curRemove.setAmount(curRemove.getAmount() - removeAmount);
					}
				}
			}
		}
		for (Ingredient ing : remaining) {
			if (ing.getAmount() > 0) {
				return false;
			}
		}
		return true;
	}

	public boolean removeAll(IngredientList i) {
		Ingredient[] ings = i.ingredients.toArray(new Ingredient[i.ingredients.size()]);
		return removeAll(ings);
	}

	public boolean containsAll(Ingredient... i) {
		IngredientList test = new IngredientList(this);
		return test.removeAll(i);
	}

	public boolean containsAll(IngredientList i) {
		Ingredient[] ings = i.ingredients.toArray(new Ingredient[i.ingredients.size()]);
		return containsAll(ings);
	}

	public boolean contains(Ingredient i) {
		int remaining = i.getAmount();
		for (Ingredient cur : ingredients) {
			if (i.isSimilar(cur)) {
				remaining -= cur.getAmount();
				if (remaining <= 0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof IngredientList) {
			IngredientList il = (IngredientList) o;
			return ingredients.equals(il.ingredients);
		}
		return false;
	}

	public boolean removeFromInv(InventoryView view, int multiplier) {
		List<Ingredient> remaining = new ArrayList<Ingredient>();
		for (Ingredient i : ingredients) {
			remaining.add(new Ingredient(i, i.getAmount() * multiplier));
		}

		int bucketsToAdd = 0;
		int topSize = view.getTopInventory().getSize();
		int end = topSize + view.getBottomInventory().getSize();
		boolean stop = false;
		for (int i = topSize; i <= end && !stop; i++) {
			if (i == end) {
				i = InventoryView.OUTSIDE;
				stop = true;
			}
			if (view.getItem(i) == null) {
				continue;
			}
			Ingredient curRemoveFrom = new Ingredient(view.getItem(i));
			for (int wild = 0; wild <= 1 && view.getItem(i) != null; wild++) {
				for (Ingredient curRemove : remaining) {
					boolean remove = false;
					if (wild == 0) {
						if (curRemoveFrom.isSimilar(curRemove) && !curRemoveFrom.hasDataWildcard()) {
							remove = true;
						}
					} else {
						if (curRemoveFrom.isSimilar(curRemove)) {
							remove = true;
						}
					}
					if (remove) {
						int removeAmount = Math.min(curRemove.getAmount(), view.getItem(i).getAmount());
						if (view.getItem(i).getAmount() - removeAmount == 0) {
							view.setItem(i, null);
						} else {
							view.getItem(i).setAmount(view.getItem(i).getAmount() - removeAmount);
						}
						curRemove.setAmount(curRemove.getAmount() - removeAmount);

						if (curRemove.getMaterialData().getItemType() == Material.LAVA_BUCKET
								|| curRemove.getMaterialData().getItemType() == Material.WATER_BUCKET
								|| curRemove.getMaterialData().getItemType() == Material.MILK_BUCKET) {
							bucketsToAdd += removeAmount;
						}
					}
				}
			}
		}
		Inventory inv = view.getBottomInventory();
		for (int cur = 0; cur <= 1 && bucketsToAdd > 0; cur++) {
			for (int i = 0; i < inv.getSize() && bucketsToAdd > 0; i++) {
				ItemStack curItem = inv.getItem(i);
				if (cur == 0 && curItem != null && curItem.getType() == Material.BUCKET) {
					int amount = Math.min(bucketsToAdd, Material.BUCKET.getMaxStackSize() - curItem.getAmount());
					curItem.setAmount(curItem.getAmount() + amount);
					bucketsToAdd -= amount;
				} else if (cur == 1 && curItem == null) {
					int amount = Math.min(bucketsToAdd, Material.BUCKET.getMaxStackSize());
					bucketsToAdd -= amount;
					inv.setItem(i, new ItemStack(Material.BUCKET, amount));
				}
			}
		}

		for (Ingredient ing : remaining) {
			if (ing.getAmount() > 0) {
				return false;
			}
		}
		return true;
	}
}
