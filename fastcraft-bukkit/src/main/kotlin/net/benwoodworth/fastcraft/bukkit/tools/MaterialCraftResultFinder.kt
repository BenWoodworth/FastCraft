package net.benwoodworth.fastcraft.bukkit.tools

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.io.File

internal object MaterialCraftResultFinder {
    private val nmsVersion = Bukkit.getServer()::class.java.`package`.name.substring("org.bukkit.craftbukkit.".length)
    private val nms = "net.minecraft.server.$nmsVersion"
    private val obc = "org.bukkit.craftbukkit.$nmsVersion"

    /**
     * For use with CraftBukkit 1.13+.
     *
     * Lists materials and their craftingResults
     */
    fun generate(out: File) {
        val Item = Class
            .forName("$nms.Item")

        val Item_craftingResult = Item
            .getDeclaredField("craftingResult")
            .apply { isAccessible = true }

        val CraftMagicNumbers_getItem = Class
            .forName("$obc.util.CraftMagicNumbers")
            .getDeclaredMethod("getItem", Material.AIR::class.java, Short::class.java)

        val CraftItemStack_asNewCraftStack = Class
            .forName("$obc.inventory.CraftItemStack")
            .getDeclaredMethod("asNewCraftStack", Item)

        val craftingResults = sequence {
            enumValues<Material>().forEach { mat ->
                val item = CraftMagicNumbers_getItem.invoke(null, mat, 0.toShort())

                if (item != null) {
                    val craftingResult = Item_craftingResult.get(item)
                    if (craftingResult != null) {
                        val itemStack = CraftItemStack_asNewCraftStack.invoke(null, craftingResult) as ItemStack
                        yield(mat to itemStack.type)
                    }
                }
            }
        }

        out.writer().buffered().use { writer ->
            craftingResults.forEach { (material, result) ->
                writer.append("${material.name}: ${result.name}\n")
            }
        }
    }
}
