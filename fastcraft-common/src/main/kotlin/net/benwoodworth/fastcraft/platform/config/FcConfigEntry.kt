package net.benwoodworth.fastcraft.platform.config

interface FcConfigEntry {

    var string: String?
    var stringList: List<String?>?

    var int: Int?
    var intList: List<Int?>?

    var double: Double?
    var doubleList: List<Double?>?

    var boolean: Boolean?
    var booleanList: List<Boolean?>?

    fun remove()

    operator fun get(key: String): FcConfigEntry
}
