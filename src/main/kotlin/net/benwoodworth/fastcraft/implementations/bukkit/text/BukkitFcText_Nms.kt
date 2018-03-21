package net.benwoodworth.fastcraft.implementations.bukkit.text

import java.lang.reflect.Method

@Suppress("ClassName")
interface BukkitFcText_Nms : BukkitFcText {

    val chatBaseComponent: Any

    interface Factory : BukkitFcText.Factory

    @Suppress("PropertyName", "PrivatePropertyName")
    interface Reflection {

        val class_IChatBaseComponent: Class<*>
        val class_ChatModifier: Class<*>
        val class_EnumChatFormat: Class<*>

        val method_IChatBaseComponent_getText: Method

        val method_IChatBaseComponent_getChatModifier: Method

        val method_ChatModifier_getColor: Method
        val method_ChatModifier_setColor: Method

        val method_ChatModifier_isBold: Method
        val method_ChatModifier_setBold: Method

        val method_ChatModifier_isItalic: Method
        val method_ChatModifier_setItalic: Method

        val method_ChatModifier_isStrikethrough: Method
        val method_ChatModifier_setStrikethrough: Method

        val method_ChatModifier_isUnderlined: Method
        val method_ChatModifier_setUnderlined: Method

        val method_ChatModifier_isRandom: Method
        val method_ChatModifier_setRandom: Method
    }
}