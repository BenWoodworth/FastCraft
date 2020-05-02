package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound

interface BukkitFcSound : FcSound {
    val sound: Sound

    companion object {
        val FcSound.sound: Sound
            get() = (this as BukkitFcSound).sound
    }

    interface Factory : FcSound.Factory {
        fun fromSound(sound: Sound): FcSound

        companion object {
            fun FcSound.Factory.fromSound(sound: Sound): FcSound {
                return (this as Factory).fromSound(sound)
            }
        }
    }
}
