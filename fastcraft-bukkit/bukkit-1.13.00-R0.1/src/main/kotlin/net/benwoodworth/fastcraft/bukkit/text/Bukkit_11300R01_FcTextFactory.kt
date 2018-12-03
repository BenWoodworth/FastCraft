package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

@Suppress("ClassName")
class Bukkit_11300R01_FcTextFactory @Inject constructor(

) : FcTextFactory {

    override fun fromText(text: String): FcText {
        return Bukkit_11300R01_FcText(text = text)
    }

    override fun fromTranslatable(translate: FcTranslatable): FcText {
        return Bukkit_11300R01_FcText(translate = translate)
    }

    override fun buildText(build: FcTextBuilder.() -> Unit): FcText {
        return Bukkit_11300R01_FcTextBuilder()
            .apply(build)
            .build()
    }
}
