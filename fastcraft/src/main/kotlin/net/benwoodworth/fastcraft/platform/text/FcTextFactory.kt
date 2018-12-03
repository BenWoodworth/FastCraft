package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.util.Extensible

interface FcTextFactory : Extensible {

    fun fromText(text: String): FcText

    fun fromTranslatable(translate: FcTranslatable): FcText

    fun buildText(build: FcTextBuilder.() -> Unit): FcText
}