package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound
import javax.inject.Inject

object BukkitFcSound_1_9 {
    class Sounds @Inject constructor(
    ) : BukkitFcSound.Sounds {
        override val uiButtonClick: FcSound by lazy { fromSound(Sound.UI_BUTTON_CLICK) }

        override fun fromSound(sound: Sound): FcSound {
            return BukkitFcSound_1_7(sound)
        }
    }
}
