package net.benwoodworth.fastcraft.util

sealed class CancellableResult<out T> {
    object Cancelled : CancellableResult<Nothing>()

    data class Result<out T>(
        val result: T
    ) : CancellableResult<T>()
}

fun <T> CancellableResult(result: T): CancellableResult.Result<T> {
    return CancellableResult.Result(result)
}
