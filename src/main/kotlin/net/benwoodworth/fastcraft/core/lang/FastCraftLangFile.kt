package net.benwoodworth.fastcraft.core.lang

import com.beust.klaxon.JsonObject
import com.beust.klaxon.array
import com.beust.klaxon.obj
import com.beust.klaxon.string
import net.benwoodworth.fastcraft.dependencies.text.Text
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Provider
import com.beust.klaxon.Parser as JsonParser

class FastCraftLangFile @Inject constructor(
        private val textBuilder: Provider<Text.Builder>
) : FastCraftLang {

    /*
     * https://github.com/BenWoodworth/FastCraft/tree/v1/dev/src/main/resources/lang
     * https://www.json2yaml.com/
     * https://jsfiddle.net/BenWoodworth/m13rp1mf/3/
     */

    private val globalPlaceholders = mapOf(
            "l" to "<",
            "r" to ">",

            "black" to "§0",
            "dark_blue" to "§1",
            "dark_green" to "§2",
            "dark_aqua" to "§3",
            "dark_red" to "§4",
            "dark_purple" to "§5",
            "gold" to "§6",
            "gray" to "§7",
            "dark_gray" to "§8",
            "blue" to "§9",
            "green" to "§a",
            "aqua" to "§b",
            "red" to "§c",
            "light_purple" to "§d",
            "yellow" to "§e",
            "white" to "§f",

            "obfuscate" to "§k",
            "bold" to "§l",
            "strike" to "§m",
            "underline" to "§n",
            "italic" to "§o",
            "reset" to "§r"
    )

    private lateinit var json: JsonObject

    fun load(file: Path) {
        InputStreamReader(
                Files.newInputStream(file),
                StandardCharsets.UTF_8
        ).use {
            json = JsonParser().parse(it) as JsonObject
        }
    }

    private fun String.substitute(vararg placeholders: Pair<String, String>): Text {
        return replace(Regex("<([^<>]*)>")) { value ->
            placeholders
                    .firstOrNull { it.first == value.value }?.second
                    ?: globalPlaceholders[value.value]
                    ?: "<${value.value}>"
        }.let {
            textBuilder.get()
                    .text(it)
                    .build()
        }
    }

    private fun List<String>.substitute(vararg placeholders: Pair<String, String>): List<Text> {
        return this.map {
            it.substitute(*placeholders)
        }
    }

    override fun guiTitle(): Text {
        return json
                .obj("gui")!!
                .string("title")!!
                .substitute()
    }

    override fun guiIngredientsLabel(): Text {
        return json
                .obj("gui")!!
                .obj("ingredients")!!
                .string("label")!!
                .substitute()
    }

    override fun guiIngredientsItem(item: String, amount: Int): Text {
        return json
                .obj("gui")!!
                .obj("ingredients")!!
                .string("item")!!
                .substitute(
                        "item" to item,
                        "amount" to amount.toString()
                )
    }

    override fun guiResultsLabel(): Text {
        return json
                .obj("gui")!!
                .obj("results")!!
                .string("label")!!
                .substitute()
    }

    override fun guiResultsItem(item: String, amount: Int): Text {
        return json
                .obj("gui")!!
                .obj("results")!!
                .string("item")!!
                .substitute(
                        "item" to item,
                        "amount" to amount.toString()
                )
    }

    override fun guiToolbarPageTitle(page: Int, total: Int): Text {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("page")!!
                .string("title")!!
                .substitute(
                        "page" to page.toString(),
                        "total" to total.toString()
                )
    }

    override fun guiToolbarPageLore(page: Int, total: Int): List<Text> {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("page")!!
                .array<String>("lore")!!
                .substitute(
                        "page" to page.toString(),
                        "total" to total.toString()
                )
    }

    override fun guiToolbarWorkbenchTitle(): Text {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("workbench")!!
                .string("title")!!
                .substitute()
    }

    override fun guiToolbarWorkbenchLore(): List<Text> {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("workbench")!!
                .array<String>("lore")!!
                .substitute()
    }

    override fun guiToolbarMultiplierTitle(multiplier: Int): Text {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("multiplier")!!
                .string("title")!!
                .substitute()
    }

    override fun guiToolbarMultiplierLore(multiplier: Int): List<Text> {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("multiplier")!!
                .array<String>("lore")!!
                .substitute()
    }

    override fun guiToolbarRefreshTitle(): Text {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("refresh")!!
                .string("title")!!
                .substitute()
    }

    override fun guiToolbarRefreshLore(): List<Text> {
        return json
                .obj("gui")!!
                .obj("toolbar")!!
                .obj("refresh")!!
                .array<String>("lore")!!
                .substitute()
    }
}