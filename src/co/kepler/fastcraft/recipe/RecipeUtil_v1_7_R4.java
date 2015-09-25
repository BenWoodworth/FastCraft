package co.kepler.fastcraft.recipe;

import java.util.HashSet;

import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_7_R4.CraftingManager;
import net.minecraft.server.v1_7_R4.IRecipe;
import net.minecraft.server.v1_7_R4.RecipeArmorDye;
import net.minecraft.server.v1_7_R4.RecipeBookClone;
import net.minecraft.server.v1_7_R4.RecipeFireworks;
import net.minecraft.server.v1_7_R4.RecipeMapClone;
import net.minecraft.server.v1_7_R4.RecipeMapExtend;
import net.minecraft.server.v1_7_R4.ShapedRecipes;
import net.minecraft.server.v1_7_R4.ShapelessRecipes;

public class RecipeUtil_v1_7_R4 extends RecipeUtil {

	public RecipeUtil_v1_7_R4() {
		badHashes = new HashSet<String>();
		for (Object o : CraftingManager.getInstance().getRecipes()) {
			IRecipe r = (IRecipe) o;
			if ((r instanceof ShapedRecipes || r instanceof ShapelessRecipes) && (
					(r instanceof RecipeArmorDye) ||
					(r instanceof RecipeBookClone) ||
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
