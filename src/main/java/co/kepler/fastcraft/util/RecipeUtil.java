package co.kepler.fastcraft.util;

import co.kepler.fastcraft.recipes.CraftingInvWrapper;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utility methods for recipes.
 */
public class RecipeUtil {
    private static boolean achievementsSupported = true;

    // Recipes that should be ignored by FastCraft
    private static Set<Integer> ignoreRecipeHashes = new HashSet<>(Arrays.asList(
            -1790165977, 434920731, 469091008, // Map cloning
            1717467320, 2127371409, // Tipped arrows
            315219687, 313670400, // Dyed leather armor
            434711233, 432506854, // Banners
            461434721, // Book cloning
            408495308, // Fireworks
            314686177 // Cap repair
    ));

    // Achievements associated with different items
    @SuppressWarnings("deprecation")
    private static Map<Material, Achievement> craftingAchievements = ImmutableMap.<Material, Achievement>builder()
            .put(Material.ENCHANTMENT_TABLE, Achievement.ENCHANTMENTS)
            .put(Material.STONE_PICKAXE, Achievement.BUILD_BETTER_PICKAXE)
            .put(Material.WOOD_PICKAXE, Achievement.BUILD_PICKAXE)
            .put(Material.WOOD_SWORD, Achievement.BUILD_SWORD)
            .put(Material.WORKBENCH, Achievement.BUILD_WORKBENCH)
            .put(Material.BOOKSHELF, Achievement.BOOKCASE)
            .put(Material.WOOD_HOE, Achievement.BUILD_HOE)
            .put(Material.FURNACE, Achievement.BUILD_FURNACE)
            .put(Material.BREAD, Achievement.MAKE_BREAD)
            .put(Material.CAKE, Achievement.BAKE_CAKE).build();

    /**
     * See if a recipe should be ignored.
     *
     * @param hash The recipe hash to check.
     * @return Returns true if the recipe should be ignored.
     */
    public static boolean shouldIgnoreRecipe(int hash) {
        return ignoreRecipeHashes.contains(hash);
    }

    /**
     * See if a recipe should be ignored.
     *
     * @param recipe The recipe to check.
     * @return Returns true if the recipe should be ignored.
     */
    public static boolean shouldIgnoreRecipe(Recipe recipe) {
        return shouldIgnoreRecipe(hashRecipe(recipe));
    }

    /**
     * Award a player an achievement for crafting an item.
     *
     * @param player      The player to award the achievement to.
     * @param craftedItem The item the player crafted.
     */
    @SuppressWarnings("deprecation")
    public static void awardAchievement(Player player, ItemStack craftedItem) {
        if (!achievementsSupported) {
            return;
        }

        try {
            Achievement a = craftingAchievements.get(craftedItem.getType());
            if (a == null) return;
            if (player.hasAchievement(a)) return;
            if (a.getParent() == null || player.hasAchievement(a.getParent())) {
                player.awardAchievement(a);
            }
        } catch (UnsupportedOperationException e) {
            achievementsSupported = false;
        }
    }

    /**
     * See if two recipes are equal.
     *
     * @param r0 A recipe to compare.
     * @param r1 A recipe to compare.
     * @return Returns true if the recipes are equal.
     */
    public static boolean areEqual(Recipe r0, Recipe r1) {
        if (r0 == r1) return true; // Are the same objects
        if (r0 == null ^ r1 == null) return false; // One is null, and one is not
        if (!r0.getResult().equals(r1.getResult())) return false; // Recipes have different results

        if (r0 instanceof ShapedRecipe) {
            if (!(r1 instanceof ShapedRecipe)) return false; // Not the same type of recipe
            ShapedRecipe sr0 = (ShapedRecipe) r0, sr1 = (ShapedRecipe) r1;

            return areShapesEqual(sr0, sr1); // Compare the shapes
        } else if (r0 instanceof ShapelessRecipe) {
            if (!(r1 instanceof ShapelessRecipe)) return false; // Not the same type of recipe
            ShapelessRecipe sr0 = (ShapelessRecipe) r0, sr1 = (ShapelessRecipe) r1;

            return sr0.getIngredientList().equals(sr1.getIngredientList()); // Compare ingredients
        } else if (r0 instanceof FurnaceRecipe) {
            if (!(r1 instanceof FurnaceRecipe)) return false; // Not the same type of recipe
            FurnaceRecipe fr0 = (FurnaceRecipe) r0, fr1 = (FurnaceRecipe) r1;

            return fr0.getInput().equals(fr1.getInput()); // Compare inputs
        }
        return false; // Unable to compare
    }

    /**
     * Compare the shape of two shaped recipes.
     *
     * @param sr0 A shaped recipe to compare.
     * @param sr1 A shaped recipe to compare.
     * @return Returns true if the recipes' shapes are the same.
     */
    public static boolean areShapesEqual(ShapedRecipe sr0, ShapedRecipe sr1) {
        String[] shape0 = sr0.getShape(), shape1 = sr1.getShape();
        if (shape0.length != shape1.length) return false; // Different number of rows

        int rows = shape0.length;
        ItemStack[][] items0 = new ItemStack[rows][], items1 = new ItemStack[rows][];
        for (int row = 0; row < shape0.length; row++) {
            char[] row0 = shape0[row].toCharArray(), row1 = shape1[row].toCharArray();
            if (row0.length != row1.length) return false; // Different number of columns
            int cols = row0.length;

            items0[row] = new ItemStack[cols];
            items1[row] = new ItemStack[cols];

            for (int col = 0; col < cols; col++) {
                items0[row][col] = sr0.getIngredientMap().get(row0[col]);
                items1[row][col] = sr1.getIngredientMap().get(row1[col]);
            }
        }

        for (int row = 0; row < items0.length; row++) {
            if (!Arrays.equals(items0[row], items1[row])) return false;
        }

        return true;
    }

    /**
     * Get a recipe's matrix of ingredients in the crafting table.
     *
     * @param recipe The recipe to get the matrix of.
     * @return Return a matrix, or null if the recipe can't fit in a crafting grid.
     */
    public static ItemStack[] getRecipeMatrix(Recipe recipe) {
        assert recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe :
                "Recipe must be a ShapedRecipe or a ShapelessRecipe";

        ItemStack[] matrix = new ItemStack[9];
        if (recipe instanceof ShapedRecipe) {
            ShapedRecipe sr = (ShapedRecipe) recipe;
            Map<Character, ItemStack> ingredients = sr.getIngredientMap();
            String[] shape = sr.getShape();

            // Add items to the matrix
            if (shape.length > 3) return null;
            for (int row = 0; row < shape.length; row++) {
                String curRow = shape[row];
                if (curRow.length() > 3) return null;
                for (int col = 0; col < curRow.length(); col++) {
                    matrix[row * 3 + col] = ingredients.get(curRow.charAt(col));
                }
            }
        } else {
            ShapelessRecipe sr = (ShapelessRecipe) recipe;

            // Add items to the matrix
            int matIndex = 0;
            for (ItemStack item : sr.getIngredientList()) {
                ItemStack curStack = item.clone();
                curStack.setAmount(0);
                for (int i = 0; i < item.getAmount(); i++) {
                    if (matIndex >= matrix.length) return null;
                    matrix[matIndex++] = curStack;
                }
            }
        }
        return matrix;
    }

    /**
     * Call a PrepareItemCraftEvent for a player, with a matrix and a result item.
     *
     * @param player The player who will be opening the table.
     * @param matrix The matrix of items in the crafting grid.
     * @param result The item in the result slot of the crafting table.
     * @return Returns the called event.
     */
    public static PrepareItemCraftEvent callPrepareItemCraftEvent(Player player, Recipe recipe, ItemStack[] matrix,
                                                                  ItemStack result, Location location) {
        assert player != null : "Player must not be null";
        assert recipe != null : "Recipe must not be null";
        assert matrix != null : "Matrix must not be null";

        CraftingInvWrapper inv = new CraftingInvWrapper(player, location);
        inv.setRecipe(recipe);
        inv.setMatrix(matrix);
        inv.setResult(result);

        PrepareItemCraftEvent event = new PrepareItemCraftEvent(inv, inv.getView(player), false);
        Bukkit.getPluginManager().callEvent(event);

        return event;
    }

    /**
     * Call the CraftItemEvent to see if it's cancelled.
     *
     * @return Returns false if the event was cancelled.
     */
    public static boolean callCraftItemEvent(Player player, Recipe recipe, ItemStack[] matrix, Location location) {
        assert player != null : "Player must not be null";
        assert recipe != null : "Recipe must not be null";
        assert matrix != null : "Matrix must not be null";

        CraftingInvWrapper inv = new CraftingInvWrapper(player, location);
        inv.setResult(recipe.getResult());
        inv.setRecipe(recipe);
        inv.setMatrix(matrix);
        inv.getViewers().add(player);

        CraftItemEvent event = new CraftItemEvent(recipe, inv.getView(player),
                InventoryType.SlotType.RESULT, 0, ClickType.UNKNOWN, InventoryAction.UNKNOWN);

        Bukkit.getPluginManager().callEvent(event);
        return !event.isCancelled() && event.getResult() != Event.Result.DENY;
    }

    /**
     * Get a hashcode for a recipe.
     *
     * @param r The recipe to hash.
     * @return Returns the generated hashcode.
     */
    public static int hashRecipe(Recipe r) {
        if (r == null) return 0;
        ItemStack result = r.getResult();
        int hash = result == null ? 0 : result.hashCode();
        if (r instanceof ShapedRecipe) {
            ShapedRecipe sr = (ShapedRecipe) r;
            for (String s : sr.getShape()) {
                for (char c : s.toCharArray()) {
                    hash *= 31;
                    ItemStack is = sr.getIngredientMap().get(c);
                    hash += is == null ? 0 : is.hashCode();
                }
            }
        } else if (r instanceof ShapelessRecipe) {
            hash *= 31;
            ShapelessRecipe sr = (ShapelessRecipe) r;
            for (ItemStack is : sr.getIngredientList()) {
                hash += is.hashCode();
            }
        }
        return hash;
    }

    /**
     * Clone a recipe.
     *
     * @param toClone The recipe to clone.
     * @return Returns the cloned recipe.
     */
    @SuppressWarnings("deprecation")
    public static Recipe cloneRecipe(Recipe toClone) {
        if (toClone == null) return null;
        if (toClone instanceof ShapedRecipe) {
            ShapedRecipe recipe = (ShapedRecipe) toClone;
            ShapedRecipe result;

            try {
                result = new ShapedRecipe(
                        ((ShapedRecipe) toClone).getKey(),
                        recipe.getResult().clone()
                );
            } catch (NoSuchMethodError e) { // Fallback
                result = new ShapedRecipe(recipe.getResult().clone());
            }

            // Copy recipe shape
            String[] shape = recipe.getShape();
            result.shape(Arrays.copyOf(shape, shape.length));

            // Copy ingredients map
            Map<Character, ItemStack> ingredients = recipe.getIngredientMap();
            for (char c : ingredients.keySet()) {
                ItemStack item = ingredients.get(c);
                if (item == null) continue;
                result.setIngredient(c, item.getData().clone());
            }
            return result;
        } else if (toClone instanceof ShapelessRecipe) {
            ShapelessRecipe recipe = (ShapelessRecipe) toClone;
            ShapelessRecipe result;

            try {
                result = new ShapelessRecipe(
                        ((ShapelessRecipe) toClone).getKey(),
                        recipe.getResult().clone()
                );
            } catch (NoSuchMethodError e) { // Fallback
                result = new ShapelessRecipe(recipe.getResult().clone());
            }

            // Copy ingredients
            for (ItemStack ingredient : recipe.getIngredientList()) {
                result.addIngredient(ingredient.getAmount(), ingredient.getData());
            }
            return result;
        } else if (toClone instanceof FurnaceRecipe) {
            FurnaceRecipe recipe = (FurnaceRecipe) toClone;
            return new FurnaceRecipe(recipe.getResult().clone(), recipe.getInput().getData());
        }

        throw new UnsupportedOperationException(
                "Unable to clone recipes of type " + toClone.getClass().getName()
        );
    }

    public static ComparableRecipe comparable(Recipe recipe) {
        return new ComparableRecipe(recipe);
    }

    public static class ComparableRecipe {
        public final Recipe recipe;
        private final int hash;

        public ComparableRecipe(Recipe base) {
            recipe = base;
            hash = hashRecipe(recipe);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ComparableRecipe)) return false;
            return areEqual(recipe, ((ComparableRecipe) o).recipe);
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }
}
