package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import javax.inject.Inject

class BukkitFcTextFactory_1_13_00_R01 @Inject constructor(

) : BukkitFcTextFactory {

    override fun fromText(text: String): FcText {
        return BukkitFcText_1_13_00_R01(text = text)
    }

    override fun fromTranslatable(translate: FcTranslatable): FcText {
        return BukkitFcText_1_13_00_R01(translate = translate)
    }

    override fun buildText(build: FcTextBuilder.() -> Unit): FcText {
        return BukkitFcTextBuilder_1_13_00_R01()
            .apply(build)
            .build()
    }
}
