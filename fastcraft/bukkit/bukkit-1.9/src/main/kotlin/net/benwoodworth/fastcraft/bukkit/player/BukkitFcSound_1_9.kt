package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcSound_1_9 {
    @Singleton
    class Factory @Inject constructor(
    ) : BukkitFcSound_1_7.Factory() {
        override val uiButtonClick: FcSound
            get() = fromSound(Sound.UI_BUTTON_CLICK)
    }
}
