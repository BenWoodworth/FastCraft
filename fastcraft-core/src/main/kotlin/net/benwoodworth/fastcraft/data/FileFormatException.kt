package net.benwoodworth.fastcraft.data

import java.io.IOException

class FileFormatException(
    message: String? = null,
    cause: Throwable? = null,
) : IOException(message, cause)
