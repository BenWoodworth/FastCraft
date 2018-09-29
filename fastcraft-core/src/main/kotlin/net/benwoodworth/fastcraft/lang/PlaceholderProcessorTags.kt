package net.benwoodworth.fastcraft.lang

import javax.inject.Inject

/**
 * Substitutes <tags> in localization strings.
 */
class PlaceholderProcessorTags @Inject constructor(
) : PlaceholderProcessor {

    override fun sub(string: String, placeholders: Map<String, String>): String {
        return string.replace(Regex("<([^<>]*)>")) {
            placeholders[it.value]
                ?: globalPlaceholders[it.value]
                ?: "<${it.value}>"
        }
    }
}