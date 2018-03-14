package net.benwoodworth.fastcraft.implementations.bukkit.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText

interface BukkitFcText {

    interface Builder : FcText.Builder {

        interface Specific : FcText.Builder.Specific {

        }
    }
}