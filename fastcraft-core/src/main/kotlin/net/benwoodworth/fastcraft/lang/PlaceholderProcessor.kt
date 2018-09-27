package net.benwoodworth.fastcraft.lang

/**
 * Substitutes placeholders in language strings
 */
interface PlaceholderProcessor {

    /**
     * Substitute placeholders in a string.
     *
     * @param string the string to substitute.
     * @param placeholders the values to substitute.
     * @return the [string] with values substituted.
     */
    fun sub(string: String, placeholders: Map<String, String> = emptyMap()): String

    /**
     * Substitute placeholders in a string list.
     *
     * @param strings the strings to substitute.
     * @param placeholders the values to substitute.
     * @return the [strings] with values substituted.
     */
    fun sub(strings: List<String>, placeholders: Map<String, String> = emptyMap()): List<String> {
        return strings.map { sub(it, placeholders) }
    }
}