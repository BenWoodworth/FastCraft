package net.benwoodworth.fastcraft.data

fun ByteArray.toInt(): Int {
    return fold(0) { r, b -> (r shl 8) or (b.toInt() and 0xFF) }
}

fun ByteArray.toLong(): Long {
    return fold(0L) { r, b -> (r shl 8) or (b.toLong() and 0xFF) }
}
