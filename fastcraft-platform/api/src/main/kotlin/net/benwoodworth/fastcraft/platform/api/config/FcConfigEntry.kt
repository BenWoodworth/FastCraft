package net.benwoodworth.fastcraft.platform.api.config

interface FcConfigEntry {

    var string: String?
    var stringList: List<String?>?
    var stringMap: Map<String, String?>

    var int: Int?
    var intList: List<Int?>?
    var intMap: Map<String, Int?>?

    var double: Double?
    var doubleList: List<Double?>?
    var doubleMap: Map<String, Double?>?

    var boolean: Boolean?
    var booleanList: List<Boolean?>?
    var booleanMap: Map<String, Boolean?>?

    fun remove()

    operator fun get(key: String): FcConfigEntry
}
