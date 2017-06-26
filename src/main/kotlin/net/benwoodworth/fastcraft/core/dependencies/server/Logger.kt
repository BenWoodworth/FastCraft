package net.benwoodworth.fastcraft.core.dependencies.server

/**
 * Logs messages to the server console.
 */
interface Logger {

    /**
     * Log an error to the console.
     *
     * @param message the error message to log
     */
    fun logError(message: String)

    /**
     * Log info to the console.
     *
     * @param message the info to log
     */
    fun logInfo(message: String)

    /**
     * Log a warning to the console.
     *
     * @param message the warning to log
     */
    fun logWarning(message: String)

    /**
     * Log a debug message to the console, only if debug output is enabled.
     *
     * @param message the debug message to log
     */
    fun logDebug(message: String)
}
