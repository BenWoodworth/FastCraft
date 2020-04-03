package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter

interface BukkitFcTextConverter : FcTextConverter {
    fun toRaw(text: FcText): String

    companion object {
        fun FcTextConverter.toRaw(text: FcText): String {
            return (this as BukkitFcTextConverter).toRaw(text)
        }
    }
}
