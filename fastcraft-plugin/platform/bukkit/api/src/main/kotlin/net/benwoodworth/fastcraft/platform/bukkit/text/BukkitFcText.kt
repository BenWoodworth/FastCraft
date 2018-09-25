package net.benwoodworth.fastcraft.platform.bukkit.text

import net.benwoodworth.fastcraft.platform.api.text.FcText

interface BukkitFcText : FcText {

    interface Factory : FcText.Factory

    interface Builder : FcText.Builder
}