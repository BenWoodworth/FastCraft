package net.benwoodworth.fastcraft.bukkit.text

import net.benwoodworth.fastcraft.platform.text.FcTextBuilder

@Suppress("ClassName")
object `BukkitFcTextBuilder$1_13_R0_1` : FcTextBuilder {

    override fun text(text: String): `BukkitFcTextBuilderTyped$1_13_R0_1` {
        return `BukkitFcTextBuilderTyped$1_13_R0_1`(text)
    }

    override fun translation(key: String): `BukkitFcTextBuilderTyped$1_13_R0_1` {
        return text("[translation(key: $key)]") // TODO
    }
}
