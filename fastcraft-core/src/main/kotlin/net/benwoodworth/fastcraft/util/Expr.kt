package net.benwoodworth.fastcraft.util

data class Expr(val expression: String) {
    fun toRegex(): Regex {
        return Regex.escape(expression)
            .replace("*", """\E.*\Q""")
            .let { Regex("^$it$") }
    }
}
