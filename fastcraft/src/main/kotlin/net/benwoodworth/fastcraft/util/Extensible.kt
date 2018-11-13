package net.benwoodworth.fastcraft.util

interface Extensible

interface Extends<out T : Extensible> : Extensible {

    val base: T
}

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
