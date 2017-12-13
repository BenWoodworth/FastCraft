package net.benwoodworth.fastcraft.core.lang

/**
 * Substitutes <tags> in localization strings.
 */
class PlaceholderProcessorTags : PlaceholderProcessor {

    override fun sub(string: String, placeholders: Map<String, String>): String {
        return string.replace(Regex("<([^<>]*)>")) {
            placeholders[it.value]
                    ?: globalPlaceholders[it.value]
                    ?: "<${it.value}>"
        }
    }
}