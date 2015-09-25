package co.kepler.fastcraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import co.kepler.fastcraft.config.PermissionsConfig;
import co.kepler.fastcraft.config.PermissionsConfig.FcPerm;
import co.kepler.fastcraft.config.PlayersConfig;
import co.kepler.fastcraft.recipe.FastRecipe;
import co.kepler.fastcraft.recipe.Ingredient;
import co.kepler.fastcraft.recipe.IngredientList;

/**
 * Manages FastCraft interface
 * 
 * @author Kepler_
 */
public class InventoryManager implements Listener {
	public static final int
	BUTTON_PREV_SLOT = 45,
	BUTTON_NEXT_SLOT = 53,
	BUTTON_AMOUNT_SLOT = 48,
	BUTTON_CRAFT_SLOT = 49,
	BUTTON_REFRESH_SLOT = 50,
	NUM_CRAFTING_ROWS = 5;

	public static final int
	CRAFT_CLICK = 0,
	CRAFT_SHIFT = 1,
	CRAFT_DROP = 2;
	
	private HashMap<String, FastCraftInv> inventories = new HashMap<String, FastCraftInv>();
	private HashMap<String, Integer> tempDisabled = new HashMap<String, Integer>();

	public void closeInventories() {
		for (String s : inventories.keySet()) {
			Player p = FastCraft.configs().players.getOnlinePlayerFromID(s);
			if (p != null) {
				p.closeInventory();
			}
		}
	}

	private boolean isInvOpen(Player player) {
		return inventories.get(FastCraft.configs().players.getID(player)) != null;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (event.isCancelled() || !(event.getPlayer() instanceof Player) ||
				event.getInventory().getType() != InventoryType.WORKBENCH) {
			return;
		}

		PlayersConfig players = FastCraft.configs().players;
		PermissionsConfig perms = FastCraft.configs().permissions;

		Player player = (Player) event.getPlayer();
		String id = players.getID(player);
		if (checkIfTempDisabled(player)) {
			return;
		}

		boolean hasPerm = perms.playerHas(player, FcPerm.USE);
		if (players.getFastCraftEnabled(player) && hasPerm) {
			FastCraftInv inv = new FastCraftInv();
			inv.setIngredients(new IngredientList(player));
			inv.updateInv(event.getInventory().getHolder());
			inventories.put(id, inv);
			event.setCancelled(true);
			event.getPlayer().openInventory(inv.getCurrentInv());
		}
	}

	public HashMap<String, FastCraftInv> getInventories() {
		return inventories;
	}

	public void tempDisable(Player p) {
		tempDisable(FastCraft.configs().players.getID(p));
	}

	public void tempDisable(String id) {
		Integer prev = tempDisabled.get(id);
		tempDisabled.put(id, prev == null ? 1 : ++prev);
	}

	private boolean checkIfTempDisabled(Player p) {
		String id = FastCraft.configs().players.getID(p);
		Integer val = tempDisabled.get(id);
		if (val == null) {
			return false;
		} if (val <= 1) {
			tempDisabled.remove(id);
			return val == 1;
		} else {
			tempDisabled.put(id, val - 1);
			return true;
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (!(event.getPlayer() instanceof Player)) {
			return;
		}
		inventories.remove(FastCraft.configs().players.getID((Player) event.getPlayer()));
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		inventories.remove(FastCraft.configs().players.getID(event.getPlayer()));
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryDrag(final InventoryDragEvent event) {
		if (!(event.getWhoClicked() instanceof Player) || event.isCancelled()) {
			return;
		}
		final Player player = (Player) event.getWhoClicked();
		String id = FastCraft.configs().players.getID(player);

		if (!isInvOpen(player)) {
			return;
		}
		List<Integer> toRemove = new ArrayList<Integer>();
		int removedItems = 0;
		for (int i : event.getRawSlots()) {
			if (i < event.getView().getTopInventory().getSize()) {
				toRemove.add(i);
				removedItems += event.getNewItems().get(i).getAmount();
			}
		}
		for (int i : toRemove) {
			event.getRawSlots().remove(i);
		}

		if (event.getCursor() == null) {
			event.setCursor(event.getOldCursor());
			event.getCursor().setAmount(removedItems);
		} else {
			event.getCursor().setAmount(event.getCursor().getAmount() + removedItems);
		}

		final FastCraftInv inv = inventories.get(id);
		final Inventory topInv = event.getView().getTopInventory();
		Inventory bottomInv = event.getView().getBottomInventory();
		inv.setIngredients(new IngredientList(bottomInv.getContents()));
		for (ItemStack is : event.getNewItems().values()) {
			inv.getIngredients().add(new Ingredient(is, is.getAmount()));
		}
		inv.updateInv(topInv.getHolder());

		Bukkit.getScheduler().scheduleSyncDelayedTask(FastCraft.get(), new Runnable() {
			@Override
			public void run() {
				topInv.setContents(inv.getCurrentInv().getContents());
				player.setItemOnCursor(event.getCursor());
			}
		}, 1);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClick(final InventoryClickEvent event) {
		if (event.isCancelled() || event instanceof CraftItemEvent ||
				!(event.getWhoClicked() instanceof Player)) {
			return;
		}
		if (!isInvOpen((Player) event.getWhoClicked()) || event.isCancelled() ||
				event.getResult() == Result.DENY || event.getRawSlot() < 0) {
			return;
		}

		final Player player = (Player) event.getWhoClicked();
		String id = FastCraft.configs().players.getID(player);
		FastCraftInv inv = inventories.get(id);
		Inventory topInv = event.getView().getTopInventory();
		Inventory bottomInv = event.getView().getBottomInventory();
		ItemStack clickedItem = event.getView().getItem(event.getRawSlot());

		boolean updateInv = false;
		if (event.getRawSlot() >= 0 && event.getRawSlot() < event.getView().getTopInventory().getSize()) {
			// Clicked in top inventory
			event.setResult(Result.DENY);
			if (clickedItem != null && clickedItem.getType() != Material.AIR) {
				boolean clickSound = true;
				if (event.getSlot() == BUTTON_PREV_SLOT) {
					inv.setPage(inv.getPage() - 1);
					updateInv = true;
				} else if (event.getSlot() == BUTTON_NEXT_SLOT) {
					inv.setPage(inv.getPage() + 1);
					updateInv = true;
				} else if (event.getSlot() == BUTTON_CRAFT_SLOT) {
					tempDisable(id);
					Bukkit.getScheduler().scheduleSyncDelayedTask(FastCraft.get(), new Runnable() {
						@Override
						public void run() {
							player.openWorkbench(null, true);
						}
					}, 1);
				} else if (event.getSlot() == BUTTON_REFRESH_SLOT) {
					inv.resetCraftableItems();
					updateInv = true;
				} else if (event.getSlot() == BUTTON_AMOUNT_SLOT) {
					if (event.getClick() == ClickType.LEFT) {
						inv.increaseCraftAmount();
						updateInv = true;
					} else if (event.getClick() == ClickType.RIGHT) {
						inv.decreaseCraftAmount();
						updateInv = true;
					}
				} else {
					clickSound = false;
					int index = inv.getPage() * NUM_CRAFTING_ROWS * 9 + event.getSlot();
					FastRecipe curRecipe = null;
					if (index < inv.getCraftableItems().size()) {
						curRecipe = inv.getCraftableItems().get(index);
					}

					if (curRecipe != null && curRecipe.canCraft(inv.getIngredients())) {
						final Inventory fakeTopInv = curRecipe.createCraftingInventory(event.getWhoClicked());
						InventoryView fakeView = new InventoryView() {
							@Override
							public Inventory getBottomInventory() {
								return event.getView().getBottomInventory();
							}

							@Override
							public HumanEntity getPlayer() {
								return event.getWhoClicked();
							}

							@Override
							public Inventory getTopInventory() {
								return fakeTopInv;
							}

							@Override
							public InventoryType getType() {
								return fakeTopInv.getType();
							}
						};

						CraftItemEvent craftEvent = new CraftItemEvent(curRecipe.getRecipe(), fakeView, SlotType.RESULT,
								0, // Crafting result slot ID
								event.getClick(), event.getAction());

						ItemStack startItem = craftEvent.getCurrentItem();
						Bukkit.getServer().getPluginManager().callEvent(craftEvent);

						boolean isCancelled = craftEvent.isCancelled();
						boolean resultDeny = craftEvent.getResult() == Result.DENY;

						if (isCancelled || resultDeny) {
							event.setCancelled(craftEvent.isCancelled());
							event.setResult(craftEvent.getResult());
							return;
						}

						ItemStack recipeResult = craftEvent.getCurrentItem();
						craftEvent.setCurrentItem(startItem);

						Achievement a = FastCraft.get().getAchievements()
								.get(recipeResult.getType());
						if (a != null) {
							try {
								if (a != null && !player.hasAchievement(a) &&
										player.hasAchievement(a.getParent())) {
									player.awardAchievement(a);
								}
							} catch (NoSuchMethodError e) {
								player.awardAchievement(a);
							}
						}

						switch (event.getClick()) {
						case LEFT:
						case RIGHT:
						case DOUBLE_CLICK:
							craftItems(curRecipe, inv.getCraftAmount(), CRAFT_CLICK, event);
							updateInv = true;
							break;
						case SHIFT_LEFT:
						case SHIFT_RIGHT:
							craftItems(curRecipe, inv.getCraftAmount(), CRAFT_SHIFT, event);
							updateInv = true;
							break;
						case CONTROL_DROP:
						case DROP:
							craftItems(curRecipe, inv.getCraftAmount(), CRAFT_DROP, event);
							updateInv = true;
							break;
						case MIDDLE:
							if (player.getGameMode() == GameMode.CREATIVE
							&& player.getItemOnCursor().getType() == Material.AIR) {
								ItemStack newCursor = new ItemStack(event.getCurrentItem());
								newCursor.setAmount(newCursor.getMaxStackSize());
								player.setItemOnCursor(newCursor);
							}
							break;
						case NUMBER_KEY:
						default:
							break;
						}
					}
				}
				if (clickSound) {
					player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
				}
			}
		} else if (event.getRawSlot() > 0) {
			// Clicked in bottom inventory
			event.setResult(Result.DENY);
			switch (event.getClick()) {
			case CONTROL_DROP:
				player.getInventory().setItem(InventoryView.OUTSIDE, event.getCurrentItem());
				bottomInv.setItem(event.getSlot(), null);
				break;
			case DROP:
				ItemStack toDrop = new ItemStack(event.getCurrentItem());
				if (event.getCurrentItem().getAmount() > 1) {
					event.getCurrentItem().setAmount(event.getCurrentItem().getAmount() - 1);
					toDrop.setAmount(1);
				} else {
					event.setCurrentItem(null);
				}
				player.getInventory().setItem(InventoryView.OUTSIDE, toDrop);
				break;
			case LEFT:
				if (event.getCurrentItem().getType() == Material.AIR) {
					event.setCurrentItem(player.getItemOnCursor());
					player.setItemOnCursor(null);
				} else if (player.getItemOnCursor().getType() == Material.AIR) {
					player.setItemOnCursor(event.getCurrentItem());
					event.setCurrentItem(null);
				} else if (event.getCurrentItem().isSimilar(player.getItemOnCursor())) {
					int move = Math.min(event.getCurrentItem().getMaxStackSize() - event.getCurrentItem().getAmount(),
							player.getItemOnCursor().getAmount());
					event.getCurrentItem().setAmount(event.getCurrentItem().getAmount() + move);
					if (move == player.getItemOnCursor().getAmount()) {
						player.setItemOnCursor(null);
					} else {
						player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() - move);
					}
				} else {
					ItemStack newCursor = event.getCurrentItem();
					ItemStack newInvItem = player.getItemOnCursor();
					player.setItemOnCursor(newCursor);
					event.setCurrentItem(newInvItem);
				}
				break;
			case RIGHT:
				if (event.getCurrentItem().getType() == Material.AIR) {
					if (player.getItemOnCursor().getAmount() > 1) {
						event.setCurrentItem(player.getItemOnCursor());
						event.getCurrentItem().setAmount(1);
						player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() - 1);
					} else {
						event.setCurrentItem(player.getItemOnCursor());
						player.setItemOnCursor(null);
					}
				} else if (player.getItemOnCursor().getType() == Material.AIR) {
					int take = (int) Math.ceil(event.getCurrentItem().getAmount() / 2.);
					if (take == event.getCurrentItem().getAmount()) {
						player.setItemOnCursor(event.getCurrentItem());
						event.setCurrentItem(null);
					} else {
						ItemStack newCursor = new ItemStack(event.getCurrentItem());
						newCursor.setAmount(take);
						player.setItemOnCursor(newCursor);
						event.getCurrentItem().setAmount(event.getCurrentItem().getAmount() - take);
					}
				} else if (event.getCurrentItem().isSimilar(player.getItemOnCursor())) {
					int move = Math.min(event.getCurrentItem().getMaxStackSize() - event.getCurrentItem().getAmount(),
							1);
					event.getCurrentItem().setAmount(event.getCurrentItem().getAmount() + move);
					if (move == player.getItemOnCursor().getAmount()) {
						player.setItemOnCursor(null);
					} else {
						player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() - move);
					}
				} else {
					ItemStack newCursor = event.getCurrentItem();
					ItemStack newInvItem = player.getItemOnCursor();
					player.setItemOnCursor(newCursor);
					event.setCurrentItem(newInvItem);
				}
				break;
			case MIDDLE:
				if (player.getGameMode() == GameMode.CREATIVE && player.getItemOnCursor().getType() == Material.AIR) {
					ItemStack newCursor = new ItemStack(event.getCurrentItem());
					newCursor.setAmount(newCursor.getMaxStackSize());
					player.setItemOnCursor(newCursor);
				}
				break;
			case NUMBER_KEY:
				if (event.getHotbarButton() != event.getSlot()) {
					ItemStack oldNumbItem = bottomInv.getItem(event.getHotbarButton());
					bottomInv.setItem(event.getHotbarButton(), event.getCurrentItem());
					event.setCurrentItem(oldNumbItem);
				}
				break;
			case WINDOW_BORDER_LEFT:
				event.getView().setItem(InventoryView.OUTSIDE, player.getItemOnCursor());
				player.setItemOnCursor(null);
				break;
			case WINDOW_BORDER_RIGHT:
				if (player.getItemOnCursor().getAmount() <= 1) {
					event.getView().setItem(InventoryView.OUTSIDE, player.getItemOnCursor());
					player.setItemOnCursor(null);
				} else {
					ItemStack itemToDrop = new ItemStack(player.getItemOnCursor());
					itemToDrop.setAmount(1);
					event.getView().setItem(InventoryView.OUTSIDE, itemToDrop);
					player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() - 1);
				}
				break;
			case UNKNOWN:
				updateInv = true;
			case DOUBLE_CLICK:
				for (int i = 9; i < 45; i++) {
					int slot = i % 36;
					ItemStack cur = bottomInv.getItem(slot);
					if (event.getCursor().isSimilar(cur)) {
						int amount = cur.getAmount();
						if (amount + event.getCursor().getAmount() > cur.getMaxStackSize()) {
							amount = cur.getMaxStackSize() - event.getCursor().getAmount();
						}
						if (amount == cur.getAmount()) {
							bottomInv.setItem(slot, null);
						} else {
							cur.setAmount(cur.getAmount() - amount);
						}
						player.getItemOnCursor().setAmount(event.getCursor().getAmount() + amount);
						if (event.getCursor().getAmount() == event.getCursor().getMaxStackSize()) {
							break;
						}
					}
				}
				break;
			case SHIFT_RIGHT:
			case SHIFT_LEFT:
				ItemStack item = event.getCurrentItem();
				if (item == null) {
					break;
				}
				boolean top = event.getSlot() < 9;
				int start = top ? 9 : 0;
				int end = top ? 36 : 9;
				for (int i = start; i < end; i++) {
					ItemStack cur = bottomInv.getItem(i);
					if (item.isSimilar(cur)) {
						int add = cur.getMaxStackSize() - cur.getAmount();
						if (add <= 0) {
							continue;
						}
						add = Math.min(add, item.getAmount());
						cur.setAmount(cur.getAmount() + add);
						if (item.getAmount() == add) {
							item = null;
							event.setCurrentItem(null);
							break;
						} else {
							item.setAmount(item.getAmount() - add);
						}
					}
				}
				if (item == null) {
					break;
				}
				for (int i = start; i < end; i++) {
					if (bottomInv.getItem(i) == null) {
						bottomInv.setItem(i, item);
						event.setCurrentItem(null);
						break;
					}
				}
				break;
			default:
				break;
			}
			updateInv = true;
		}
		if (updateInv) {
			inv.setIngredients(new IngredientList(bottomInv.getContents()));
			inv.updateInv(topInv.getHolder());
			topInv.setContents(inv.getCurrentInv().getContents());
		}
	}

	private ItemStack shiftAddToInv(ItemStack item, Inventory inv) {
		int remaining = item.getAmount();
		for (int i = 0; i <= 1; i++) {
			for (int rawRow = inv.getSize() / 9; rawRow > 0; rawRow--) {
				int row = (rawRow == inv.getSize() / 9) ? 0 : rawRow;
				for (int column = 8; column >= 0; column--) {
					int slot = row * 9 + column;
					ItemStack curItem = inv.getItem(slot);
					if (i == 0 && curItem != null && curItem.isSimilar(item)) {
						int toAdd = Math.min(item.getMaxStackSize() - curItem.getAmount(), remaining);
						curItem.setAmount(curItem.getAmount() + toAdd);
						remaining -= toAdd;
					} else if (i == 1 && curItem == null) {
						int toAdd = Math.min(remaining, item.getMaxStackSize());
						ItemStack newItem = new ItemStack(item);
						newItem.setAmount(toAdd);
						remaining -= toAdd;
						inv.setItem(slot, newItem);
					}
					if (remaining == 0) {
						return null;
					}
				}
			}
		}
		ItemStack notAdded = new ItemStack(item);
		notAdded.setAmount(remaining);
		return notAdded;
	}

	private void craftItems(FastRecipe recipe, int amount, int craftType, InventoryClickEvent e) {
		if (amount == 0) amount = recipe.getResult().getAmount();
		ItemStack result = recipe.getResult();
		Inventory inv = e.getWhoClicked().getInventory();
		if (amount == 0) amount = result.getAmount();
		
		// See how many items can be crafted
		int maxCraftAmount = 0;
		int maxStackSize = result.getMaxStackSize();
		if (craftType == CRAFT_SHIFT) {
			for (int i = 0; i < inv.getSize(); i++) {
				ItemStack cur = inv.getItem(i);
				if (cur == null) {
					maxCraftAmount += maxStackSize;
				} else if (result.isSimilar(cur) && cur.getAmount() < maxStackSize) {
					maxCraftAmount += maxStackSize - cur.getAmount();
				}
			}
		} else if (craftType == CRAFT_DROP) {
			maxCraftAmount = amount;
		} else if (craftType == CRAFT_CLICK) {
			if (e.getCursor().getType() == Material.AIR) {
				maxCraftAmount = result.getMaxStackSize();
			} else if (result.isSimilar(e.getCursor())) {
				maxCraftAmount = result.getMaxStackSize() - e.getCursor().getAmount();
			}
		}
		if (maxCraftAmount <= 0) return;
		maxCraftAmount /= recipe.getResult().getAmount();
		maxCraftAmount = Math.min(maxCraftAmount, recipe.getMaxCraftAmount(inv));

		// How many times the recipe will be crafted
		int craftAmount = Math.min(maxCraftAmount, amount / recipe.getResult().getAmount());

		// Remove ingredients from the player's inventory
		recipe.getIngredients().removeFromInv(e.getView(), craftAmount);
		
		// Give player items
		ItemStack crafted = recipe.getResult().clone();
		crafted.setAmount(crafted.getAmount() * craftAmount);
		if (craftType == CRAFT_SHIFT) {
			shiftAddToInv(crafted, inv);
		} else if (craftType == CRAFT_DROP) {
			e.getView().setItem(InventoryView.OUTSIDE, crafted);
		} else if (craftType == CRAFT_CLICK) {
			if (e.getCursor().getType() == Material.AIR) {
				e.getWhoClicked().setItemOnCursor(crafted);
			} else {
				int newAmount = e.getCursor().getAmount() + crafted.getAmount();
				e.getCursor().setAmount(newAmount);
			}
		}
	}
}
