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
    buildActions: B.() -> Unit
): T {
    buildActions(this)
    return build()
}

inline operator fun <T, B : Builder<T>> Provider<B>.invoke(
    buildActions: B.() -> Unit
): T {
    val builder = get()
    buildActions(builder)
    return builder.build()
}
