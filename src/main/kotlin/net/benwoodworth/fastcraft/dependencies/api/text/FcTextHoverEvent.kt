package net.benwoodworth.fastcraft.dependencies.api.text

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem

interface FcTextHoverEvent {

    interface Factory {
        fun showText(text: List<FcText?>): FcTextHoverEvent
        fun showAchievement(achievementId: String): FcTextHoverEvent
        fun showItem(item: FcItem): FcTextHoverEvent
        fun showEntity(vararg entity: FcText): FcTextHoverEvent
    }
}