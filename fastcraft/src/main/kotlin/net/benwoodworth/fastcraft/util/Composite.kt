package net.benwoodworth.fastcraft.util

interface Composite<out T : Any> {

    val base: T?
}
