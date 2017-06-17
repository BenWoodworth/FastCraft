package net.benwoodworth.fastcraft.core.dependencies

/**
 * Logs messages to the server console.
 */
interface ConsoleLogger {

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

    /**
     * Log a debug message to the console, only if debug output is enabled.
     *
     * @param message the debug message to log
     */
    fun debug(message: String)
}
