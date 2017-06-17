package net.benwoodworth.fastcraft.core.dependencies

/**
 * Logs messages to the server console.
 */
abstract class LogAdapter(baseConsole: Any) : Adapter(baseConsole) {

    /**
     * Log an error to the console.
     *
     * @param message the error message to log
     */
    abstract fun error(message: String)

    /**
     * Log info to the console.
     *
     * @param message the info to log
     */
    abstract fun info(message: String)

    /**
     * Log a warning to the console.
     *
     * @param message the warning to log
     */
    abstract fun warn(message: String)

    /**
     * Log a debug message to the console, only if debug output is enabled.
     *
     * @param message the debug message to log
     */
    abstract fun debug(message: String)
}
