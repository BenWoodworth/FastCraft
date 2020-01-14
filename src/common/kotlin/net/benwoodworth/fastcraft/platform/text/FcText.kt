package net.benwoodworth.fastcraft.platform.text

interface FcText {
    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    fun toPlaintext(): String
}
