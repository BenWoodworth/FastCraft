package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import javax.inject.Inject

class BukkitFcRawTextFactory_1_13_00_R01 @Inject constructor(
) : BukkitFcRawTextFactory {

    override fun BukkitFcRawText(text: FcText): BukkitFcRawText {
        return BukkitFcRawText_1_13_00_R01(text)
    }
}