package net.benwoodworth.fastcraft.util

interface Extends<out T : Extendable> : Extendable {

    val base: T
}
