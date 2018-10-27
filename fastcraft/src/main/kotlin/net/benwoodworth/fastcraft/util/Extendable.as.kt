package net.benwoodworth.fastcraft.util

inline fun <reified T> Extendable.`as`(): T {
    var result = this

    while (result is Extends<*>) {
        when (result) {
            is T -> return result
            else -> result = result.base
        }
    }

    return result as T
}

inline fun <reified T> Extendable.`as?`(): T? {
    var result: Any? = this

    while (result is Extends<*>) {
        when (result) {
            is T -> return result
            else -> result = result.base
        }
    }

    return result as? T
}
