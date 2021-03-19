package net.benwoodworth.fastcraft.sponge.util

import java.util.*

fun <T> Optional<T>.orNull(): T? = orElse(null)
