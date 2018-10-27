package net.benwoodworth.fastcraft.util

interface Extends<out T : Extensible> : Extensible {

    val base: T
}
