package net.benwoodworth.fastcraft.util

import java.lang.annotation.Inherited
import javax.inject.Provider

@DslMarker
@Inherited
@Retention(AnnotationRetention.BINARY)
annotation class BuilderDsl

@BuilderDsl
interface Builder<out T> {

    fun build(): T
}

inline operator fun <T, B : Builder<T>> B.invoke(
    build: B.() -> Unit
): T {
    build(this)
    return build()
}

inline operator fun <T, B : Builder<T>> Provider<B>.invoke(
    build: B.() -> Unit
): T {
    val builder = get()
    build(builder)
    return builder.build()
}
