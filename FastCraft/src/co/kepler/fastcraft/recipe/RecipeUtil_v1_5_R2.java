package co.kepler.fastcraft.recipe;

import java.util.HashSet;

import org.bukkit.craftbukkit.v1_5_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_5_R2.CraftingManager;
import net.minecraft.server.v1_5_R2.IRecipe;
import net.minecraft.server.v1_5_R2.RecipeArmorDye;
import net.minecraft.server.v1_5_R2.RecipeFireworks;
import net.minecraft.server.v1_5_R2.RecipeMapClone;
import net.minecraft.server.v1_5_R2.RecipeMapExtend;
import net.minecraft.server.v1_5_R2.ShapedRecipes;
import net.minecraft.server.v1_5_R2.ShapelessRecipes;

public class RecipeUtil_v1_5_R2 extends RecipeUtil {

	public RecipeUtil_v1_5_R2() {
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
