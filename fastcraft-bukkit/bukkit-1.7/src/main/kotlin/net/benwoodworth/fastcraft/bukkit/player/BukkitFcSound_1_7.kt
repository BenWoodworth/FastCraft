package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcSound_1_7 {
    @Singleton
    class TypeClass @Inject constructor(
    ) : BukkitFcSound.TypeClass {
        override val FcSound.sound: Sound
            get() = value as Sound
    }

    @Singleton
    class Factory @Inject constructor(
    ) : BukkitFcSound.Factory {
        override val uiButtonClick: FcSound
            get() = fromSound(Sound.CLICK)

        override fun fromSound(sound: Sound): FcSound {
            return FcSound(sound)
        }
    }
}
