package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayoutGrid
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Server
import javax.inject.Inject

class BukkitFcGuiFactory_1_15_00_R01 @Inject constructor(
    private val server: Server,
    private val textConverter: BukkitFcTextConverter,
    private val guiFactory: BukkitFcGui_1_15_00_R01Factory,
    private val guiLayoutGridFactory: BukkitFcGuiLayoutGrid_1_15_00_R01Factory
) : BukkitFcGuiFactory {
    override fun openChestGui(player: FcPlayer, title: FcText?, height: Int): FcGui<FcGuiLayoutGrid> {
        val legacyTitle = title?.let {
            textConverter.toLegacy(it, player.locale)
        }

        return guiFactory.create(
            player,
            { owner ->
                when (legacyTitle) {
                    null -> server.createInventory(owner, 9 * height)
                    else -> server.createInventory(owner, 9 * height, legacyTitle)
                }
            },
            { inventory -> guiLayoutGridFactory.create(9, height, inventory, player.locale) }
        )
    }
}
