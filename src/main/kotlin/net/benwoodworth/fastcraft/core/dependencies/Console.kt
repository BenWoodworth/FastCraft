package net.benwoodworth.fastcraft.core.dependencies

import net.benwoodworth.fastcraft.core.dependencies.text.Text

/**
 * Logs messages to the server console.
 */
interface Console {

    /**
     * Log an error to the console.
     *
     * @param message the error message to log
     */
    fun logError(message: Text)

    /**
     * Log info to the console.
     *
     * @param message the info to log
     */
    fun logInfo(message: Text)

    /**
     * Log a warning to the console.
     *
     * @param message the warning to log
     */
    fun logWarning(message: Text)

    /**
     * Log a debug message to the console, only if debug output is enabled.
     *
     * @param message the debug message to log
     */
    fun logDebug(message: Text)
}
