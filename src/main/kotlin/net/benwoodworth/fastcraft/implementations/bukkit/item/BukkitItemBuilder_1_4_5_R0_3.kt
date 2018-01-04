package net.benwoodworth.fastcraft.implementations.bukkit.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitReflectFactory
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

open class BukkitItemBuilder_1_4_5_R0_3 : ItemBuilder {

    private companion object {
        private val reflectFactory = BukkitReflectFactory() // TODO Use DI

        val keyClass = reflectFactory.getNmsClass("MinecraftKey")
        val keyConstructor = keyClass.getConstructor(String::class.java)!!

        val registryClass = reflectFactory.getNmsClass("RegistryMaterials")
        val registryGetMethod = registryClass.getMethod("get", String::class.java)!!

        val itemClass = reflectFactory.getNmsClass("Item")
        val itemRegistryField = itemClass.getField("REGISTRY")!!
        val itemGetIdMethod = itemClass.getMethod("getId", itemClass)!!
    }

    protected var result = ItemStack(Material.AIR)

    override fun build() = BukkitItem(result.clone())

    override fun from(item: Item) = also {
        result = (item.mutableCopy() as BukkitItem.Mutable).base
    }

    override fun type(typeId: String) = also {
        val key = keyConstructor.newInstance(typeId)
        val registry = itemRegistryField.get(null)
        val item = registryGetMethod.invoke(registry, key)
        val idNumber = itemGetIdMethod.invoke(item) as Int

        @Suppress("DEPRECATION")
        result.type = Material.getMaterial(idNumber)
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

    override fun durability(durability: Int) = also {
        result.durability = durability.toShort()
    }
}