package net.benwoodworth.fastcraft.util

inline fun Composite<*>.unbox(condition: (Any) -> Boolean): Any? {
    var result: Any? = this

    while (result != null && !condition(result)) {
        result = (result as? Composite<*>)?.base
    }

    return result
}

inline fun <reified T : Any> Composite<*>.unbox(): T? {
    return unbox { it is T } as T?
}
