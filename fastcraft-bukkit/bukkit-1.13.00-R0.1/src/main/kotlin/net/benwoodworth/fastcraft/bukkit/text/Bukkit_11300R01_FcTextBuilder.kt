package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextBuilder

@Suppress("ClassName")
object Bukkit_11300R01_FcTextBuilder : FcTextBuilder {

    override fun text(text: String): Bukkit_11300R01_FcTextBuilderTyped {
        return Bukkit_11300R01_FcTextBuilderTyped(text)
    }

    override fun translation(key: String): Bukkit_11300R01_FcTextBuilderTyped {
        return text("[translation(key: $key)]") // TODO
    }
}
