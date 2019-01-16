package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class BukkitFcTextFactory @Inject constructor(

) : FcTextFactory {

    override fun fromText(text: String): FcText {
        return BukkitFcText(text = text)
    }

    override fun fromTranslatable(translate: FcTranslatable): FcText {
        return BukkitFcText(translate = translate)
    }

    override fun buildText(build: FcTextBuilder.() -> Unit): FcText {
        return BukkitFcTextBuilder()
            .apply(build)
            .build()
    }
}
