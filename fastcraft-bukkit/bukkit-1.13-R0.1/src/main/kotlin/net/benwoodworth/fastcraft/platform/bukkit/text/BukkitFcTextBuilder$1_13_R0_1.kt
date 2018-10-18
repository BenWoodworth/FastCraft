package net.benwoodworth.fastcraft.platform.bukkit.text

@Suppress("ClassName")
object `BukkitFcTextBuilder$1_13_R0_1` : BukkitFcTextBuilder {

    override fun text(text: String): `BukkitFcTextBuilderTyped$1_13_R0_1` {
        return `BukkitFcTextBuilderTyped$1_13_R0_1`(text)
    }

    override fun translation(key: String): `BukkitFcTextBuilderTyped$1_13_R0_1` {
        return text("[translation(key: $key)]") // TODO
    }
}
