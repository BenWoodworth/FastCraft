package net.benwoodworth.fastcraft.bukkit.tools

import org.bukkit.Bukkit
import org.bukkit.Material
import java.io.File

internal object MaterialItemIdFinder {
    private val nmsVersion = Bukkit.getServer()::class.java.`package`.name.substring("org.bukkit.craftbukkit.".length)
    private val nms = "net.minecraft.server.$nmsVersion"
    private val obc = "org.bukkit.craftbukkit.$nmsVersion"

    /**
     * For use with CraftBukkit 1.7-1.12
     *
     * Lists materials and their minecraft item ids
     */
    @Suppress("UNCHECKED_CAST", "LocalVariableName", "DEPRECATION")
    fun generate(out: File) {
        val Item = Class.forName("$nms.Item")

        val Item_getId = Item
            .getDeclaredMethod("getId", Item)
            .apply { isAccessible = true }

        val Item_REGISTRY = Item
            .getDeclaredField("REGISTRY")
            .apply { isAccessible = true }

        val RegistryMaterials = Class.forName("$nms.RegistryMaterials")

        val RegistryMaterials_idItems = RegistryMaterials.superclass
            .getDeclaredField("c")
            .apply { isAccessible = true }

        val registry = Item_REGISTRY.get(null)
        val registryIdItems = RegistryMaterials_idItems.get(registry) as Map<*, *>

        val materialItemIds = registryIdItems
            .map { (key, item) -> Item_getId.invoke(null, item) to key.toString().split(":")[1] }
            .flatMap { (intId, itemId) ->
                enumValues<Material>()
                    .filter { it.id == intId }
                    .map { it to itemId }
            }
            .sortedBy { (material, _) -> material.name }

        out.writer().buffered().use { writer ->
            materialItemIds.forEach { (material, itemId) ->
                writer.append("${material.name}: ${itemId}\n")
            }
        }
    }
}
