package net.benwoodworth.fastcraft.util

data class Expr(val expression: String) {
    fun toRegex(): Regex {
        if (expression.length >= 2 &&
            expression.startsWith('/') &&
            expression.endsWith('/')
        ) {
            return Regex(expression.substring(1 until expression.lastIndex))
        }

        return Regex.escape(expression)
            .replace("*", """\E.*\Q""")
            .let { Regex("^$it$") }
    }
}
