package net.benwoodworth.fastcraft.util

@Suppress("unused")
sealed class Optional<out T> {

    class Empty<out T> internal constructor() : Optional<T>() {

//        @Deprecated("Always returns false")
//        val hasValue = false
    }

    class WithValue<out T> internal constructor(val value: T) : Optional<T>() {

//        @Deprecated("Always returns true")
//        val hasValue = true
    }
}

private val empty = Optional.Empty<Any>()

fun <T> optional(): Optional<T> {
    @Suppress("UNCHECKED_CAST")
    return empty as Optional<T>
}

fun <T> optional(value: T): Optional<T> {
    return Optional.WithValue(value)
}

fun <T> T.asOptional(): Optional<T> {
    return Optional.WithValue(this)
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
