package co.kepler.fastcraft.recipe;

import java.util.HashSet;

import org.bukkit.craftbukkit.v1_8_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R2.CraftingManager;
import net.minecraft.server.v1_8_R2.IRecipe;
import net.minecraft.server.v1_8_R2.RecipeArmorDye;
import net.minecraft.server.v1_8_R2.RecipeBookClone;
import net.minecraft.server.v1_8_R2.RecipeFireworks;
import net.minecraft.server.v1_8_R2.RecipeMapClone;
import net.minecraft.server.v1_8_R2.RecipeMapExtend;
import net.minecraft.server.v1_8_R2.RecipeRepair;
import net.minecraft.server.v1_8_R2.RecipesBanner;
import net.minecraft.server.v1_8_R2.ShapedRecipes;
import net.minecraft.server.v1_8_R2.ShapelessRecipes;

public class RecipeUtil_v1_8_R2 extends RecipeUtil {

	public RecipeUtil_v1_8_R2() {
		badHashes = new HashSet<String>();
		for (IRecipe r : CraftingManager.getInstance().getRecipes()) {
			if ((r instanceof ShapedRecipes || r instanceof ShapelessRecipes) && (
					(r instanceof RecipeArmorDye) ||
					(r instanceof RecipeBookClone) ||
					(r instanceof RecipeMapClone) ||
					(r instanceof RecipeMapExtend) ||
					(r instanceof RecipeFireworks) ||
					(r instanceof RecipeRepair) ||
					RecipesBanner.class.equals(r.getClass().getEnclosingClass()))) {
				badHashes.add(new FastRecipe(r.toBukkitRecipe()).getHash());
			}
		}
	}

    public String getItemName(ItemStack is) {
    	return CraftItemStack.asNMSCopy(is).getName();
    }
}
