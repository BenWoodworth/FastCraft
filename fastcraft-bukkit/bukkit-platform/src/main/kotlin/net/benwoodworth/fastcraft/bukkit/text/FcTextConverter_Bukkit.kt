package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextConverter

interface FcTextConverter_Bukkit : FcTextConverter {
    fun toRaw(text: FcText): String
}

fun FcTextConverter.toRaw(text: FcText): String {
    return (this as FcTextConverter_Bukkit).toRaw(text)
}
