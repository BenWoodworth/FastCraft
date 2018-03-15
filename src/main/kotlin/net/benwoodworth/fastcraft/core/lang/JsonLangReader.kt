package net.benwoodworth.fastcraft.core.lang

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.obj
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Provider

class JsonLangReader @Inject constructor(
        private val placeholderProcessor: PlaceholderProcessorTags,
        private val textBuilder: Provider<FcText.Builder>
) {

    private lateinit var json: JsonObject

    fun load(stream: InputStream) {
        InputStreamReader(stream).use {
            json = Parser().parse(it) as JsonObject
        }
    }

    fun load(file: Path) {
        load(file.toFile().inputStream())
    }

    private fun missing(path: String): String {
        return "[$path]"
    }

    private fun get(path: String): Any? {
        var obj: JsonObject? = json
        val fields = path.split('.')
        for (field in fields.dropLast(1)) {
            obj = obj?.obj(field)
        }

        return obj?.get(fields.lastOrNull())
    }

    @Suppress("UNCHECKED_CAST")
    fun list(path: String, placeholders: Map<String, String> = emptyMap()): List<FcText> {
        val result = (get(path) as? JsonArray<String>)?.let {
            placeholderProcessor.sub(it, placeholders)
        } ?: listOf(missing(path))

        return result.map {
            textBuilder.get()
                    .setText(it)
                    .build()
        }
    }

    fun string(path: String, placeholders: Map<String, String> = emptyMap()): FcText {
        val result = (get(path) as? String)?.let {
            placeholderProcessor.sub(it, placeholders)
        } ?: missing(path)

        return textBuilder.get()
                .setText(result)
                .build()
    }

}