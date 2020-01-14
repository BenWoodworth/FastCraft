package net.benwoodworth.fastcraft.util

fun <T, R> Sequence<T>.uniqueBy(selector: (T) -> R): Sequence<T> {
    val found = mutableSetOf<R>()
    return filter { found.add(selector(it)) }
}
