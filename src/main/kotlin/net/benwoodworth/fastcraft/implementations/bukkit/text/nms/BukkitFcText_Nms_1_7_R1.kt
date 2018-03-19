package net.benwoodworth.fastcraft.implementations.bukkit.text.nms

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitReflectionFactory
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Proxy
import javax.inject.Inject

@Suppress("ClassName")
class BukkitFcText_Nms_1_8_R1 @Inject constructor(
) : BukkitFcText_Nms {

    class Factory(
            private val reflectionFactory: BukkitReflectionFactory
    ) : BukkitFcText_Nms.Factory {

        override fun <T : Any> delegateINamable(type: Class<T>, iNamable: T, name: BukkitFcText_Nms): T {
            val iInventory = reflectionFactory.getNmsClass("IInventory")
            val getScoreboardDisplayName = iInventory.getMethod("getScoreboardDisplayName")

            @Suppress("UNCHECKED_CAST")
            return Proxy.newProxyInstance(
                    javaClass.classLoader,
                    arrayOf(type)
            ) { proxy, method, args ->
                if (method == getScoreboardDisplayName) {
                    name
                } else {
                    method.invoke(iNamable, *args)
                }
            } as T
        }

        override fun getItemName(item: ItemStack): BukkitFcText {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemDisplayName(item: ItemStack): FcText? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun setItemDisplayName(item: ItemStack, displayName: FcText?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemLore(item: ItemStack): List<FcText?>? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun setItemLore(item: ItemStack, lore: List<FcText?>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun sendPlayerMessage(player: Player, message: FcText) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun buildText(text: String): FcText.Builder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    fun delegateINamable(iNameable: Any, name: BukkitFcText_Nms) {

    }
}