package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound

interface BukkitFcSound : FcSound {
    val sound: Sound

    interface Factory : FcSound.Factory {
        fun fromSound(sound: Sound): FcSound
    }
}

val FcSound.sound: Sound
    get() = (this as BukkitFcSound).sound

fun FcSound.Factory.fromSound(sound: Sound): FcSound {
    return (this as BukkitFcSound.Factory).fromSound(sound)
}
