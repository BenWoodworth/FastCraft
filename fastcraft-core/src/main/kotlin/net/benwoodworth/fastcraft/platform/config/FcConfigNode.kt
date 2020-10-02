package net.benwoodworth.fastcraft.platform.config

interface FcConfigNode {
    val path: String

    fun getChildKeys(): Set<String>

    operator fun get(key: String): FcConfigNode

    fun set(value: Any?)

    operator fun set(key: String, value: Any?) = this[key].set(value)

    fun getString(): String?

    fun getStringList(): List<String?>?

    fun getInt(): Int?

    fun getIntList(): List<Int?>?

    fun getDouble(): Double?

    fun getDoubleList(): List<Double?>?

    fun getBoolean(): Boolean?

    fun getBooleanList(): List<Boolean?>?
}
