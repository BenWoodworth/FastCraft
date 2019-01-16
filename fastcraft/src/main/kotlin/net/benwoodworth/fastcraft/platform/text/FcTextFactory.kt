package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable

interface FcTextFactory {

    fun fromText(text: String): FcText

    fun fromTranslatable(translate: FcTranslatable): FcText

    fun buildText(build: FcTextBuilder.() -> Unit): FcText
}