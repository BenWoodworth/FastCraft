package net.benwoodworth.fastcraft.bukkit.tools

import org.bukkit.Bukkit
import org.bukkit.Material
import java.io.File

internal object MaterialOrderGenerator {
    private val nmsVersion = Bukkit.getServer()::class.java.`package`.name.substring("org.bukkit.craftbukkit.".length)
    private val nms = "net.minecraft.server.$nmsVersion"
    private val obc = "org.bukkit.craftbukkit.$nmsVersion"

    /**
     * For use with CraftBukkit 1.13+.
     *
     * Lists materials in the order they appear in the Minecraft item registry. (Same as in the creative menu)
     *
     * For new Minecraft releases, use this to generate a new file into bukkit/item-order/<version>.txt.
     */
    fun generate(out: File) {
        val IRegistry = Class.forName("$nms.IRegistry")
        val IRegistry_ITEM = IRegistry.getDeclaredField("ITEM")
        val IRegistry_getKey = IRegistry.getDeclaredMethod("getKey", Any::class.java)

        val itemRegistry = IRegistry_ITEM.get(null) as Iterable<*>

        val Material_getKey = Material::class.java.getDeclaredMethod("getKey")

        val itemIdsToMaterials = enumValues<Material>()
            .map { Material_getKey.invoke(it).toString() to it }
            .toMap()

        val orderedItemIds = itemRegistry.asSequence()
            .map { item -> IRegistry_getKey.invoke(itemRegistry, item) }
            .map { it.toString() }

        val orderedMaterials = orderedItemIds
            .mapNotNull { itemId ->
                itemIdsToMaterials[itemId]
                    .also { if (it == null) println("ID doesn't match a material: $it") }
            }

        out.writer().buffered().use { writer ->
            orderedMaterials.forEach { material ->
                writer.append("${material.name}\n")
            }
        }
    }
}
