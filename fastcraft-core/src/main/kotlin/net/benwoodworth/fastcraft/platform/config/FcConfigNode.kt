package net.benwoodworth.fastcraft.platform.config

interface FcConfigNode {
    operator fun get(key: String): FcConfigNode

    operator fun set(key: String, value: Any?)

    fun createNode(): FcConfigNode

    fun removeNode()

    fun getString(): String?

    fun getStringList(): List<String?>?

    fun getInt(): Int?

    fun getIntList(): List<Int?>?

    fun getDouble(): Double?

    fun getDoubleList(): List<Double?>?

    fun getBoolean(): Boolean?

    fun getBooleanList(): List<Boolean?>?
}
