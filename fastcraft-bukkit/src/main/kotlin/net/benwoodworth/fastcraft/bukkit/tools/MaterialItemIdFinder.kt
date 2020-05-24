package net.benwoodworth.fastcraft.bukkit.tools

import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import org.bukkit.Bukkit
import org.bukkit.Material
import java.io.File

internal object MaterialItemIdFinder {
    private val nmsVersion = Bukkit.getServer()::class.java.`package`.name.substring("org.bukkit.craftbukkit.".length)
    private val nms = "net.minecraft.server.$nmsVersion"
    private val obc = "org.bukkit.craftbukkit.$nmsVersion"

    private abstract class ClassCompanion(`class`: String) {
        @get:JvmName("class")
        val `class`: Class<*> = Class.forName(`class`)
    }

    private data class NamespacedId(val namespacedId: Any) {
        val namespace: String
            get() = toString().split(":", limit = 2).first()

        val id: String
            get() = toString().split(":", limit = 2)[1]

        override fun toString(): String = namespacedId.toString()
    }

    private data class Item(val item: Any) {
        companion object : ClassCompanion("$nms.Item") {
            val REGISTRY: RegistryMaterials
                get() = `class`
                    .getDeclaredField("REGISTRY")
                    .apply { isAccessible = true }
                    .get(null)
                    .let { RegistryMaterials(it) }

            fun getId(item: Item): Int =
                `class`
                    .getDeclaredMethod("getId", `class`)
                    .invoke(null, item.item) as Int
        }

        fun getName(): String =
            `class`
                .getDeclaredMethod("getName")
                .invoke(item) as String

        fun getVariantName(itemStack: ItemStack): String =
            `class`
                .getDeclaredMethod(
                    when (nmsVersion) {
                        "v1_7_R4" -> "a"
                        "v1_8_R3" -> "e_"
                        "v1_9_R2", "v1_10_R1" -> "f_"
                        "v1_11_R1", "v1_12_R1" -> "a"
                        else -> error(nmsVersion)
                    },
                    ItemStack.`class`,
                )
                .invoke(item, itemStack.itemStack) as String
    }


    private data class RegistryMaterials(val registry: Any) {
        companion object : ClassCompanion("$nms.RegistryMaterials")

        fun getIdItems(): Map<NamespacedId, Item> =
            `class`.superclass
                .getDeclaredField("c")
                .apply { isAccessible = true }
                .get(registry)
                .let { it as Map<*, *> }
                .mapKeys { (key, _) -> NamespacedId(key!!) }
                .mapValues { (_, value) -> Item(value!!) }
    }

    private data class ItemStack(val itemStack: Any) {
        companion object : ClassCompanion("$nms.ItemStack")

        constructor(item: Item, data: Int) : this(
            `class`
                .getConstructor(Item.`class`, Int::class.java)
                .newInstance(item.item, data)
        )

        fun setData(data: Int) {
            `class`
                .getDeclaredMethod("setData", Int::class.java)
                .invoke(itemStack, data)
        }
    }

    private fun Item.getVariantNames(): List<String>? {
        val itemStack = ItemStack(this, 0)

        val result = (0..255)
            .map { data ->
                itemStack.setData(data)
                getVariantName(itemStack)
            }
            .toMutableList()

        while (result.size > 1 && result.last() == result.first()) {
            result.removeAt(result.lastIndex)
        }

        return result
            .takeUnless { it.isEmpty() }
            ?.takeUnless { it.size == 1 && it.first() == getName() }
    }

    /**
     * For use with CraftBukkit 1.7-1.12
     *
     * Lists materials and their minecraft item ids
     */
    @Suppress("DEPRECATION")
    fun generate(outDir: File) {
        val registryIdItems = Item.REGISTRY.getIdItems()

        val materialItemIds = registryIdItems
            .map { (key, item) -> Item.getId(item) to key.id }
            .flatMap { (intId, itemId) ->
                enumValues<Material>()
                    .filter { it.id == intId }
                    .map { it to itemId }
            }
            .sortedBy { (material, _) -> material.name }

        val materialItems = registryIdItems.values
            .flatMap { item ->
                enumValues<Material>()
                    .filter { it.id == Item.getId(item) }
                    .map { it to item }
            }
            .sortedBy { (material, _) -> material.name }

        val materialItemNames = materialItems
            .map { (material, item) -> material to item.getName() }

        val materialItemVariantNames = materialItems
            .map { (material, item) -> material to item.getVariantNames() }
            .filter { (_, item) -> item != null }

        val version = BukkitVersion
            .parse(Bukkit.getBukkitVersion())
            .run { "$major.$minor" }

        val out = File(outDir, "$version.yml")
        out.writer().buffered().use { writer ->
            writer.append("item-ids:\n")
            materialItemIds.forEach { (material, itemId) ->
                writer.append("  ${material.name}: ${itemId}\n")
            }

            writer.append("item-names:\n")
            materialItemNames.forEach { (material, itemName) ->
                writer.append("  ${material.name}: ${itemName}\n")
            }

            writer.append("item-variant-names:\n")
            materialItemVariantNames.forEach { (material, itemName) ->
                writer.append("  ${material.name}: ${itemName}\n")
            }
        }
    }
}
