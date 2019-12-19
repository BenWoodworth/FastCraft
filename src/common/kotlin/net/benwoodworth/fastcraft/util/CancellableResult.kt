package net.benwoodworth.fastcraft.util

sealed class CancellableResult<out T> {
    object Cancelled : CancellableResult<Nothing>()

    class Result<out T>(
        val result: T
    ) : CancellableResult<T>()
}
