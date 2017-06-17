package net.benwoodworth.fastcraft.core.lang

/**
 * FastCraft localization.
 *
 * If you provide a localization, feel free to PM Kepler_ on spigotmc.org
 *
 * Use the section symbol (§) for formatting/color codes:
 * http://minecraft.gamepedia.com/Formatting_codes
 */
interface Lang {
    companion object {
        val localizations = hashMapOf(
                "en-us" to LangEnUs(),
                "zh-cn" to LangZhCn(),
                "zh-tw" to LangZhTw(),
                "cs" to LangCs(),
                "de" to LangDe(),
                "nl" to LangNl(),
                "pl" to LangPl(),
                "ru" to LangRu(),
                "tr" to LangTr(),
                "vi" to LangVi()
        )
    }

    fun gui_title() = "FastCraft"

    fun gui_ingredients_label(): String
    fun gui_ingredients_item(item: String, amount: Int) = "§a ${amount}x $item"

    fun gui_results_label(): String
    fun gui_results_item(item: String, amount: Int) = "§a ${amount}x $item"

    fun gui_recipeHash_label(): String
    fun gui_recipeHash_code(hashcode: String) = "§7 $hashcode"

    fun gui_toolbar_prev_title(cur: Int, prev: Int, total: Int): String
    fun gui_toolbar_prev_lore(cur: Int, prev: Int, total: Int): List<String>

    fun gui_toolbar_next_title(cur: Int, next: Int, total: Int): String
    fun gui_toolbar_next_lore(cur: Int, next: Int, total: Int): List<String>

    fun gui_toolbar_refresh_title(): String
    fun gui_toolbar_refresh_lore(): List<String>

    fun gui_toolbar_multiplier_title(multiplier: Int): String
    fun gui_toolbar_multiplier_lore(multiplier: Int): List<String>

    fun gui_toolbar_workbench_title(): String
    fun gui_toolbar_workbench_lore(): List<String>

    fun command_err_usage(commandUsage: String): String
    fun command_err_noPerm(permission: String): String
    fun command_err_playerOnly(): String
    fun command_err_consoleOnly(): String
    fun command_err_unknownPlayer(player: String): String

    fun command_fc_toggle_output_self_on(): String
    fun command_fc_toggle_output_self_off(): String
    fun command_fc_toggle_output_other_on(player: String): String
    fun command_fc_toggle_output_other_off(player: String): String

    fun command_fca_reload(): String
}
