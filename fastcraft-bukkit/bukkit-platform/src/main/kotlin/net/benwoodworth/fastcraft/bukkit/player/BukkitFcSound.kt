package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcSound
import org.bukkit.Sound

object BukkitFcSound {
    interface TypeClass : FcSound.TypeClass {
        val FcSound.sound: Sound
    }

    interface Factory : FcSound.Factory {
        fun fromSound(sound: Sound): FcSound
    }
}

val FcSound.TypeClass.bukkit: BukkitFcSound.TypeClass
    get() = this as BukkitFcSound.TypeClass

fun FcSound.Factory.fromSound(sound: Sound): FcSound {
    return (this as BukkitFcSound.Factory).fromSound(sound)
}
