package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound

object BukkitFcSound {
    interface Operations : FcSound.Operations {
        val FcSound.sound: Sound
    }

    interface Factory : FcSound.Factory {
        fun fromSound(sound: Sound): FcSound
    }
}

val FcSound.Operations.bukkit: BukkitFcSound.Operations
    get() = this as BukkitFcSound.Operations

fun FcSound.Factory.fromSound(sound: Sound): FcSound {
    return (this as BukkitFcSound.Factory).fromSound(sound)
}
