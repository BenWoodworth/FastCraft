package net.benwoodworth.fastcraft

inline fun <T> profile(desc: String, block: () -> T): T {
    val begin = System.nanoTime()
    println("fcprofile\t$desc\tbegin\t$begin")

    try {
        return block()
    } finally {
        val end = System.nanoTime()
        println("fcprofile\t$desc\tend\t$end\t+${end - begin}")
    }
}
