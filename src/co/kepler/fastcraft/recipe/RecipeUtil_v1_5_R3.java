package co.kepler.fastcraft.recipe;

import java.util.HashSet;

import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_5_R3.CraftingManager;
import net.minecraft.server.v1_5_R3.IRecipe;
import net.minecraft.server.v1_5_R3.RecipeArmorDye;
import net.minecraft.server.v1_5_R3.RecipeFireworks;
import net.minecraft.server.v1_5_R3.RecipeMapClone;
import net.minecraft.server.v1_5_R3.RecipeMapExtend;
import net.minecraft.server.v1_5_R3.ShapedRecipes;
import net.minecraft.server.v1_5_R3.ShapelessRecipes;

public class RecipeUtil_v1_5_R3 extends RecipeUtil {

	public RecipeUtil_v1_5_R3() {
		badHashes = new HashSet<String>();
		for (Object o : CraftingManager.getInstance().getRecipes()) {
			IRecipe r = (IRecipe) o;
			if ((r instanceof ShapedRecipes || r instanceof ShapelessRecipes) && (
					(r instanceof RecipeArmorDye) ||
					(r instanceof RecipeMapClone) ||
					(r instanceof RecipeMapExtend) ||
					(r instanceof RecipeFireworks))) {
				badHashes.add(new FastRecipe(r.toBukkitRecipe()).getHash());
			}
		}
	}

    public String getItemName(ItemStack is) {
    	return CraftItemStack.asNMSCopy(is).getName();
    }
}
