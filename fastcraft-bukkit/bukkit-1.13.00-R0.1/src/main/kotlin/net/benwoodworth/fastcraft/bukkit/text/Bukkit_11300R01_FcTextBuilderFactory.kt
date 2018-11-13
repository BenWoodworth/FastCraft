package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.platform.text.FcTextBuilderFactory

@Suppress("ClassName")
class Bukkit_11300R01_FcTextBuilderFactory : FcTextBuilderFactory {

    override fun text(value: String): FcTextBuilder {
        return Bukkit_11300R01_FcTextBuilder(text = value)
    }

    override fun translate(value: String): FcTextBuilder {
        return Bukkit_11300R01_FcTextBuilder(translate = value)
    }
}
