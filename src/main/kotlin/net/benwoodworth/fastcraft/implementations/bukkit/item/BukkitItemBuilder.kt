package net.benwoodworth.fastcraft.implementations.bukkit.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitReflectFactory
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

sealed class BukkitItemBuilder : ItemBuilder {

    protected var result = ItemStack(Material.AIR)

    override fun build() = BukkitItem(result.clone())

    override fun from(item: Item) = also {
        result = (item.mutableCopy() as BukkitItem.Mutable).base
    }

    override fun type(typeId: String) = also {
        @Suppress("DEPRECATION")
        result.type = Bukkit.getUnsafe().getMaterialFromInternalName(typeId)
    }

    override fun amount(amount: Int) = also {
        result.amount = amount
    }

    override fun displayName(displayName: Text?) = also {
        result.itemMeta = result.itemMeta.apply {
            setDisplayName((displayName as BukkitText?)?.text)
        }
    }

    override fun lore(vararg lore: Text?) = also {
        result.itemMeta = result.itemMeta.apply {
            this.lore = lore.map { (it as BukkitText?)?.text }
        }
    }

    override fun durability(durability: Int) {
        result.durability = durability.toShort()
    }

    class Api_1_7_2_R0_3 : BukkitItemBuilder()

    class Api_1_5_R0_1 : BukkitItemBuilder() {

        private companion object {
            val keyClass = BukkitReflectFactory.getNmsClass("MinecraftKey")!!
            val keyConstructor = keyClass.getConstructor(String::class.java)!!

            val registryClass = BukkitReflectFactory.getNmsClass("RegistryMaterials")!!
            val registryGetMethod = registryClass.getMethod("get", String::class.java)!!

            val itemClass = BukkitReflectFactory.getNmsClass("Item")!!
            val itemRegistryField = itemClass.getField("REGISTRY")!!
            val itemGetIdMethod = itemClass.getMethod("getId", itemClass)!!
        }

        override fun type(typeId: String) = also {
            val key = keyConstructor.newInstance(typeId)
            val registry = itemRegistryField.get(null)
            val item = registryGetMethod.invoke(registry, key)
            val idNumber = itemGetIdMethod.invoke(item) as Int

            @Suppress("DEPRECATION")
            result.type = Material.getMaterial(idNumber)
        }
    }
}