package co.kepler.fastcraft;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

/**
 * Inventory to imitate a normal crafting inventory.
 * 
 * @author Kepler_
 */
@SuppressWarnings("deprecation")
public class MyCraftingInventory implements CraftingInventory {
	private Inventory inv;
	private Recipe recipe;

	public MyCraftingInventory(Inventory i, Recipe r) {
		inv = i;
		recipe = r;
	}

	@Override
	public HashMap<Integer, ItemStack> addItem(ItemStack... arg0) throws IllegalArgumentException {
		return inv.addItem(arg0);
	}

	@Override
	public HashMap<Integer, ? extends ItemStack> all(int arg0) {
		return inv.all(arg0);
	}

	@Override
	public HashMap<Integer, ? extends ItemStack> all(Material arg0) throws IllegalArgumentException {
		return inv.all(arg0);
	}

	@Override
	public HashMap<Integer, ? extends ItemStack> all(ItemStack arg0) {
		return inv.all(arg0);
	}

	@Override
	public void clear() {
		inv.clear();
	}

	@Override
	public void clear(int arg0) {
		inv.clear(arg0);
	}

	@Override
	public boolean contains(int arg0) {
		return inv.contains(arg0);
	}

	@Override
	public boolean contains(Material arg0) throws IllegalArgumentException {
		return inv.contains(arg0);
	}

	@Override
	public boolean contains(ItemStack arg0) {
		return inv.contains(arg0);
	}

	@Override
	public boolean contains(int arg0, int arg1) {
		return inv.contains(arg0, arg1);
	}

	@Override
	public boolean contains(Material arg0, int arg1) throws IllegalArgumentException {
		return inv.contains(arg0, arg1);
	}

	@Override
	public boolean contains(ItemStack arg0, int arg1) {
		return inv.contains(arg0, arg1);
	}

	@Override
	public boolean containsAtLeast(ItemStack arg0, int arg1) {
		return inv.containsAtLeast(arg0, arg1);
	}

	@Override
	public int first(int arg0) {
		return inv.first(arg0);
	}

	@Override
	public int first(Material arg0) throws IllegalArgumentException {
		return inv.first(arg0);
	}

	@Override
	public int first(ItemStack arg0) {
		return inv.first(arg0);
	}

	@Override
	public int firstEmpty() {
		return inv.firstEmpty();
	}

	@Override
	public ItemStack[] getContents() {
		return inv.getContents();
	}

	@Override
	public InventoryHolder getHolder() {
		return inv.getHolder();
	}

	@Override
	public ItemStack getItem(int arg0) {
		return inv.getItem(arg0);
	}

	@Override
	public int getMaxStackSize() {
		return inv.getMaxStackSize();
	}

	@Override
	public String getName() {
		return inv.getName();
	}

	@Override
	public int getSize() {
		return 10;
	}

	@Override
	public String getTitle() {
		return inv.getTitle();
	}

	@Override
	public InventoryType getType() {
		return inv.getType();
	}

	@Override
	public List<HumanEntity> getViewers() {
		return inv.getViewers();
	}

	@Override
	public ListIterator<ItemStack> iterator() {
		return inv.iterator();
	}

	@Override
	public ListIterator<ItemStack> iterator(int arg0) {
		return inv.iterator(arg0);
	}

	@Override
	public void remove(int arg0) {
		inv.remove(arg0);
	}

	@Override
	public void remove(Material arg0) throws IllegalArgumentException {
		inv.remove(arg0);
	}

	@Override
	public void remove(ItemStack arg0) {
		inv.remove(arg0);
	}

	@Override
	public HashMap<Integer, ItemStack> removeItem(ItemStack... arg0) throws IllegalArgumentException {
		return inv.removeItem(arg0);
	}

	@Override
	public void setContents(ItemStack[] arg0) throws IllegalArgumentException {
		inv.setContents(arg0);
	}

	@Override
	public void setItem(int arg0, ItemStack arg1) {
		inv.setItem(arg0, arg1);
	}

	@Override
	public void setMaxStackSize(int arg0) {
		inv.setMaxStackSize(arg0);
	}

	@Override
	public ItemStack[] getMatrix() {
		ItemStack[] result = new ItemStack[9];
		for (int i = 0; i < 9; i++) {
			result[i] = inv.getItem(i + 1);
		}
		return result;
	}

	@Override
	public Recipe getRecipe() {
		return recipe;
	}

	@Override
	public ItemStack getResult() {
		return inv.getItem(0);
	}

	@Override
	public void setMatrix(ItemStack[] arg0) {
		for (int i = 0; i < 9; i++) {
			inv.setItem(i + 1, arg0[i]);
		}
	}

	@Override
	public void setResult(ItemStack arg0) {
		inv.setItem(0, arg0);
	}
}
