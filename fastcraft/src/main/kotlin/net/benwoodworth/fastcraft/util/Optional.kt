package net.benwoodworth.fastcraft.util

import kotlin.contracts.contract

@Suppress("unused")
sealed class Optional<out T> {

    class Empty<out T> : Optional<T>() {

//        @Deprecated("Always returns false")
//        val hasValue = false
    }

    class WithValue<out T>(val value: T) : Optional<T>() {

//        @Deprecated("Always returns true")
//        val hasValue = true
    }
}

fun <T> optional(): Optional<T> {
    return Optional.Empty()
}

fun <T> optional(value: T): Optional<T> {
    return Optional.WithValue(value)
}

//inline val <T> Optional<T>.hasValue: Boolean
//    get() {
//        contract {
//            returns(true) implies (this@hasValue is Optional.WithValue)
//            returns(false) implies (this@hasValue is Optional.Empty)
//        }
//
//        return this is Optional.WithValue
//    }
