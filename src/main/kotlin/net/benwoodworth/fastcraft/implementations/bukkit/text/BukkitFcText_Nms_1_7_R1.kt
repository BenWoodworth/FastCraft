package net.benwoodworth.fastcraft.implementations.bukkit.text

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitReflectionFactory
import java.lang.reflect.Method
import javax.inject.Inject
import javax.inject.Singleton

@AutoFactory
@Suppress("ClassName")
class BukkitFcText_Nms_1_7_R1(
        override val chatBaseComponent: Any,

        @Provided private val reflection: BukkitFcText_Nms.Reflection
) : BukkitFcText_Nms {

    private val chatModifier: Any
        get() = reflection.method_IChatBaseComponent_getChatModifier.invoke(chatBaseComponent)

    override val text: String
        get() = reflection.method_IChatBaseComponent_getText.invoke(chatBaseComponent) as String

    override var color: BukkitFcTextColor?
        get() = reflection.method_ChatModifier_getColor.invoke(chatModifier)
        set(value) {

        }

    override var bold: Boolean?
        get() = TODO()
        set(value) {}

    override var italic: Boolean?
        get() = TODO()
        set(value) {}

    override var underlined: Boolean?
        get() = TODO()
        set(value) {}

    override var strikeThrough: Boolean?
        get() = TODO()
        set(value) {}

    override var obfuscated: Boolean?
        get() = TODO()
        set(value) {}

    override var extra: MutableList<BukkitFcText>
        get() = TODO()
        set(value) {}

    class Factory(
            private val reflectionFactory: BukkitReflectionFactory
    ) : BukkitFcText_Nms.Factory {

        override fun buildText(text: String): FcText.Builder {
            val cache =

                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    @Singleton
    @Suppress("PropertyName", "PrivatePropertyName")
    class Reflection @Inject constructor(
            private val reflect: BukkitReflectionFactory
    ) : BukkitFcText_Nms.Reflection {

        override val class_IChatBaseComponent: Class<*> by lazy {
            reflect.getNmsClass("IChatBaseComponent")
        }

        override val class_ChatModifier: Class<*> by lazy {
            reflect.getNmsClass("ChatModifier")
        }

        override val class_EnumChatFormat: Class<*> by lazy {
            reflect.getNmsClass("ChatModifier")
        }

        override val method_IChatBaseComponent_getText: Method by lazy {
            class_IChatBaseComponent.getMethod("getText")
        }

        override val method_IChatBaseComponent_getChatModifier: Method by lazy {
            class_IChatBaseComponent.getMethod("b")
        }

        override val method_ChatModifier_getColor: Method by lazy {
            class_ChatModifier.getMethod("getColor")
        }

        override val method_ChatModifier_setColor: Method by lazy {
            class_ChatModifier.getMethod("setColor", class_EnumChatFormat)
        }

        override val method_ChatModifier_isBold: Method by lazy {
            class_ChatModifier.getMethod("isBold")
        }

        override val method_ChatModifier_setBold: Method by lazy {
            class_ChatModifier.getMethod("setBold", Boolean::class.java)
        }

        override val method_ChatModifier_isItalic: Method by lazy {
            class_ChatModifier.getMethod("isItalic")
        }

        override val method_ChatModifier_setItalic: Method by lazy {
            class_ChatModifier.getMethod("setItalic", Boolean::class.java)
        }

        override val method_ChatModifier_isStrikethrough: Method by lazy {
            class_ChatModifier.getMethod("isStrikethrough")
        }

        override val method_ChatModifier_setStrikethrough: Method by lazy {
            class_ChatModifier.getMethod("setStrikethrough", Boolean::class.java)
        }

        override val method_ChatModifier_isUnderlined: Method by lazy {
            class_ChatModifier.getMethod("isUnderlined")
        }

        override val method_ChatModifier_setUnderlined: Method by lazy {
            class_ChatModifier.getMethod("setUnderlined", Boolean::class.java)
        }

        override val method_ChatModifier_isRandom: Method by lazy {
            class_ChatModifier.getMethod("isRandom")
        }

        override val method_ChatModifier_setRandom: Method by lazy {
            class_ChatModifier.getMethod("setRandom", Boolean::class.java)
        }
    }
}