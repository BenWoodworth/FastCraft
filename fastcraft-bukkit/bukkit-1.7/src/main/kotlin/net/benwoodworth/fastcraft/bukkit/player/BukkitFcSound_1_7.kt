package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound
import javax.inject.Inject

class BukkitFcSound_1_7(
    override val sound: Sound,
) : BukkitFcSound {
    class Sounds @Inject constructor(
    ) : BukkitFcSound.Sounds {
        override val uiButtonClick: FcSound by lazy { fromSound(Sound.CLICK) }

        override fun fromSound(sound: Sound): FcSound {
            return BukkitFcSound_1_7(sound)
        }
    }
}
