package net.benwoodworth.fastcraft

inline fun <T> profile(desc: String, block: () -> T): T {
    val begin = System.nanoTime()
    println("fcprofile\t$desc\tbegin\t$begin")

    val result = block()

    val end = System.nanoTime()
    println("fcprofile\t$desc\tend\t$end\t+${end - begin}")

    return result
}
