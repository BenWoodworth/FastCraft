package net.benwoodworth.fastcraft.platform.text

import net.benwoodworth.fastcraft.util.Extensible

interface FcTextBuilderFactory : Extensible {

    fun text(value: String): FcTextBuilder

    fun translate(value: String): FcTextBuilder
}
