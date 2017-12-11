package net.benwoodworth.fastcraft.core.lang

import java.nio.file.Path

class FastCraftLangFile: FastCraftLang {

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

    private

    fun load(file: Path) {

    }

    private fun substitute(string: String, placeholders: Map<String, String>): String {
        return string.replace(Regex("<([^<]*)>")) {
            placeholders[it.value]
                    ?: globalPlaceholders[it.value]
                    ?: "<${it.value}>"
        }
    }

    private fun getString(key: String, placeholders: Map<String, String>): String {
        TODO()
    }

    private fun getList(key: String, placeholders: Map<String, String>): List<String> {
        TODO()
    }

    override fun guiTitle(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiIngredientsLabel(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiIngredientsItem(item: String, amount: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiResultsLabel(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiResultsItem(item: String, amount: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarPageTitle(page: Int, total: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarPageLore(page: Int, total: Int): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarWorkbenchTitle(page: Int, total: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarWorkbenchLore(page: Int, total: Int): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarMultiplierTitle(page: Int, total: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarMultiplierLore(page: Int, total: Int): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarRefreshTitle(page: Int, total: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun guiToolbarRefreshLore(page: Int, total: Int): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}