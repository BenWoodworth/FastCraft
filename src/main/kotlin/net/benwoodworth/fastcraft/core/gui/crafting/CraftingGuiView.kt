package net.benwoodworth.fastcraft.core.gui.crafting

import net.benwoodworth.fastcraft.core.lang.FastCraftLang
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.api.gui.button.GuiButtonAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.layout.GuiLayoutBasic
import net.benwoodworth.fastcraft.dependencies.api.item.Item
import net.benwoodworth.fastcraft.dependencies.api.mvp.MvpView
import javax.inject.Inject

class CraftingGuiView @Inject constructor(
        guiFactory: GuiFactory,
        fastCraftLang: FastCraftLang
) : MvpView {




    val gui = guiFactory.withSize(9, 6, fastCraftLang.guiTitle())



    class Sidebar {
        private val layout = GuiLayoutBasic(8, 0, 1, 6)

        val mutiplier = object : GuiButtonAbstract(0, 3) {
            override fun getItem(x: Int, y: Int): Item? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

}