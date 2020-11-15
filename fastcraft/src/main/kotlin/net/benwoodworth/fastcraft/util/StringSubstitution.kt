package net.benwoodworth.fastcraft.util

private val regex = Regex("""\$(\$)|\$(\w+)|\$\{(\w+)}""")

fun String.substitute(substitutions: Map<String, String>): String {
    return replace(regex) { result ->
        when {
            result.groups[1] != null -> "$"
            result.groups[2] != null -> substitutions[result.groupValues[2]] ?: result.value
            result.groups[3] != null -> substitutions[result.groupValues[3].trim()] ?: result.value
            else -> result.value
        }
    }
}