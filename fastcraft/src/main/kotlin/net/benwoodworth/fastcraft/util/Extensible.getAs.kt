package net.benwoodworth.fastcraft.util

inline fun <reified T> Extensible.getAs(): T {
    var result = this

    while (result is Extends<*>) {
        when (result) {
            is T -> return result
            else -> result = result.base
        }
    }

    return result as T
}
