package net.benwoodworth.fastcraft.util

class JsonStringBuilder(
    val stringBuilder: StringBuilder = StringBuilder()
) {

    override fun toString(): String {
        return stringBuilder.toString()
    }

    fun appendComma(): JsonStringBuilder {
        if (stringBuilder.isEmpty()) {
            return this
        }

        when (stringBuilder.last()) {
            '{', '[', ':', ',' -> {
            }
            else -> stringBuilder.append(',')
        }

        return this
    }

    fun appendString(value: String): JsonStringBuilder {
        appendComma()

        stringBuilder.append('"')

        value.forEach { c ->
            when (c) {
                '"' -> stringBuilder.append("\\\"")
                '\\' -> stringBuilder.append("\\\\")
                '/' -> stringBuilder.append("\\/")
                '\b' -> stringBuilder.append("\\b")
                '\n' -> stringBuilder.append("\\n")
                '\r' -> stringBuilder.append("\\r")
                '\t' -> stringBuilder.append("\\t")
                else -> when (c) {
                    in '\u0000'..'\u001F' -> {
                        stringBuilder.append("\\u")
                        appendHex(c.toInt(), 4)
                    }
                    else -> stringBuilder.append(c)

                }
            }
        }

        stringBuilder.append('"')
        return this
    }

    private fun appendHex(hex: Int, minDigits: Int = 1): JsonStringBuilder {
        if (hex == 0 && minDigits <= 0) {
            return this
        }

        if (hex < 0) {
            stringBuilder.append('-')
            appendHex(-hex, minDigits)
            return this
        }

        appendHex(hex shr 4, minDigits - 1)

        when (hex and 0xF) {
            in 0x0..0x9 -> stringBuilder.append('0' + hex)
            in 0xA..0xF -> stringBuilder.append('A' + hex)
        }
        return this
    }

    fun appendBoolean(value: Boolean): JsonStringBuilder {
        appendComma()
        stringBuilder.append(if (value) "true" else "false")
        return this
    }

    inline fun appendObject(value: JsonStringBuilder.() -> Unit): JsonStringBuilder {
        appendComma()
        stringBuilder.append('{')
        value(this)
        stringBuilder.append('}')
        return this
    }

    inline fun appendArray(value: JsonStringBuilder.() -> Unit): JsonStringBuilder {
        appendComma()
        stringBuilder.append('[')
        value(this)
        stringBuilder.append(']')
        return this
    }

    inline fun appendElement(name: String, appendValue: JsonStringBuilder.() -> Unit): JsonStringBuilder {
        appendComma()
        appendString(name)
        stringBuilder.append(':')
        appendValue()
        return this
    }
}
