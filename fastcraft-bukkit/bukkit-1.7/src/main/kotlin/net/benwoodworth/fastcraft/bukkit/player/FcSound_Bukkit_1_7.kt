package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound
import javax.inject.Inject
import javax.inject.Singleton

object FcSound_Bukkit_1_7 {
    @Singleton
    class Operations @Inject constructor(
    ) : FcSound_Bukkit.Operations {
        override val FcSound.sound: Sound
            get() = value as Sound
    }

    @Singleton
    open class Factory @Inject constructor(
    ) : FcSound_Bukkit.Factory {
        override val uiButtonClick: FcSound
            get() = fromSound(Sound.CLICK)

        override fun fromSound(sound: Sound): FcSound {
            return FcSound(sound)
        }
    }
}
