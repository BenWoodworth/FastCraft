package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound

object FcSound_Bukkit {
    interface Operations : FcSound.Operations {
        val FcSound.sound: Sound
    }

    interface Factory : FcSound.Factory {
        fun fromSound(sound: Sound): FcSound
    }
}

val FcSound.Operations.bukkit: FcSound_Bukkit.Operations
    get() = this as FcSound_Bukkit.Operations

fun FcSound.Factory.fromSound(sound: Sound): FcSound {
    return (this as FcSound_Bukkit.Factory).fromSound(sound)
}
