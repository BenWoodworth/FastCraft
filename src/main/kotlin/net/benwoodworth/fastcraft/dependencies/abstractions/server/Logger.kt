package net.benwoodworth.fastcraft.dependencies.abstractions.server

/**
 * Logs plugin messages to the server console.
 */
interface Logger {

    /**
     * Log an error to the console.
     *
     * @param message the error message to log
     */
    fun error(message: String)

    /**
     * Log info to the console.
     *
     * @param message the info to log
     */
    fun info(message: String)

    /**
     * Log a warning to the console.
     *
     * @param message the warning to log
     */
    fun warn(message: String)
}
