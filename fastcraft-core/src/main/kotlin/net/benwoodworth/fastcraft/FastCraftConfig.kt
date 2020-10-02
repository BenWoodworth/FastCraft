package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import net.benwoodworth.fastcraft.util.Expr
import java.nio.file.Files
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KMutableProperty0

@Singleton
class FastCraftConfig @Inject constructor(
    private val fcConfigFactory: FcConfig.Factory,
    private val fcPluginData: FcPluginData,
    private val fcItemFactory: FcItem.Factory,
    private val fcItemStackFactory: FcItemStack.Factory,
    private val fcLogger: FcLogger,
    fcItemOperations: FcItem.Operations,
    fcItemStackOperations: FcItemStack.Operations,
) : FcItem.Operations by fcItemOperations,
    FcItemStack.Operations by fcItemStackOperations {

    private var config: FcConfig = fcConfigFactory.create()
    private var modified: Boolean = false
    private var newFile: Boolean = false

    private val header: String = """
        https://github.com/BenWoodworth/FastCraft/wiki/Configuration
    """.trimIndent()

    val disableRecipes = DisableRecipes()

    inner class DisableRecipes {
        private val node: FcConfigNode
            get() = config["disable-recipes"]

        private val none = Regex("(?" + "!)") // Matches nothing

        private var recipeIds: List<Expr> = emptyList()
            set(values) {
                field = values
                recipeIdsRegex = values
                    .takeUnless { it.isEmpty() }
                    ?.joinToString("|") { it.toRegex().toString() }
                    ?.let { Regex(it) }
                    ?: none
            }

        var recipeIdsRegex: Regex = none
            private set

        fun load() {
            node["recipe-ids"].loadExprList(::recipeIds)
        }
    }

    val recipeCalculations = RecipeCalculations()

    inner class RecipeCalculations {
        private val node: FcConfigNode
            get() = config["recipe-calculations"]

        var maxTickUsage: Double = 0.1
            private set

        fun load() {
            node["max-tick-usage"].loadDouble(::maxTickUsage, min = 0.0)
        }
    }

    val playerDefaults = PlayerDefaults()

    inner class PlayerDefaults {
        private val node: FcConfigNode
            get() = config["player-defaults"]

        var enabled: Boolean = true
            private set

        fun load() {
            node["enabled"].loadBoolean(::enabled)
        }
    }

    val layout = Layout()

    inner class Layout {
        private val node: FcConfigNode
            get() = config["layout"]

        var height: Int = 6
            private set

        val recipes = Recipes()

        inner class Recipes {
            private val node: FcConfigNode
                get() = this@Layout.node["recipes"]

            var row: Int = 0
                private set

            var column: Int = 0
                private set

            var width: Int = 7
                private set

            var height: Int = 6
                private set

            fun load() {
                node["row"].loadInt(::row, 0, this@Layout.height)
                node["column"].loadInt(::column, 0, 8)
                node["width"].loadInt(::width, 1, 9 - column)
                node["height"].loadInt(::height, 1, height - row)
            }
        }

        val buttons = Buttons()

        inner class Buttons {
            private val node: FcConfigNode
                get() = this@Layout.node["buttons"]

            val craftingGrid = Button(
                node = node["crafting-grid"],
                enable = true,
                item = fcItemStackFactory.create(fcItemFactory.craftingTable),
                row = 0,
                column = 8,
            )

            val craftAmount = Button(
                node = node["craft-amount"],
                enable = true,
                item = fcItemStackFactory.create(fcItemFactory.anvil),
                row = 1,
                column = 8,
            )

            val refresh = Button(
                node = node["refresh"],
                enable = true,
                item = fcItemStackFactory.create(fcItemFactory.netherStar),
                row = 2,
                column = 8,
            )

            val page = Button(
                node = node["page"],
                enable = true,
                item = fcItemStackFactory.create(fcItemFactory.ironSword),
                row = 5,
                column = 8,
            )

            fun load() {
                craftingGrid.load()
                craftAmount.load()
                refresh.load()
                page.load()
            }
        }

        val customButtons = CustomButtons()

        inner class CustomButtons {
            private val node: FcConfigNode
                get() = this@Layout.node["custom-buttons"]

            var buttons: Set<CustomButton> = emptySet()
                private set

            fun load() {
                buttons = node.getChildKeys()
                    .map {
                        CustomButton(
                            node = node[it],
                            enable = false,
                            item = fcItemStackFactory.create(fcItemFactory.air),
                            row = 0,
                            column = 0,
                        )
                    }
                    .let {
                        it.takeUnless { it.isEmpty() } ?: listOf(
                            CustomButton(
                                node = node["example"],
                                enable = false,
                                item = fcItemStackFactory.create(fcItemFactory.air),
                                row = 0,
                                column = 0,
                                serverCommand = "say \$player pressed a button"
                            )
                        )
                    }
                    .onEach { it.load() }
                    .toSet()

                if (buttons.isEmpty()) {

                }
            }
        }

        val background = EnableItem(
            node = this@Layout.node["background"],
            enable = false,
            item = fcItemStackFactory.create(fcItemFactory.lightGrayStainedGlassPane),
        )

        open inner class EnableItem(
            protected val node: FcConfigNode,
            enable: Boolean,
            item: FcItemStack,
        ) {
            var enable: Boolean = enable
                private set

            var item: FcItemStack = item
                private set

            private var itemId: String = item.type.id

            open fun load() {
                node["enable"].loadBoolean(::enable)
                node["item"].loadItem(::item, ::itemId)
            }
        }

        open inner class Button(
            node: FcConfigNode,
            enable: Boolean,
            item: FcItemStack,
            row: Int,
            column: Int,
        ) : EnableItem(
            node = node,
            enable = enable,
            item = item,
        ) {
            var row: Int = row
                private set

            var column: Int = column
                private set

            override fun load() {
                super.load()
                node["row"].loadInt(::row, 0, this@Layout.height - 1)
                node["column"].loadInt(::column, 0, 8)
            }
        }

        inner class CustomButton(
            node: FcConfigNode,
            enable: Boolean,
            item: FcItemStack,
            row: Int,
            column: Int,
            width: Int = 1,
            height: Int = 1,
            playerCommand: String? = null,
            serverCommand: String? = null,
        ) : Button(
            node = node,
            enable = enable,
            item = item,
            row = row,
            column = column,
        ) {
            var width: Int = width
                private set

            var height: Int = height
                private set

            var playerCommand: String? = playerCommand
                private set

            var serverCommand: String? = serverCommand
                private set

            override fun load() {
                super.load()
                width = node["width"].getInt() ?: width
                height = node["height"].getInt() ?: height
                node["player-command"].loadString(::playerCommand, playerCommand)
                node["server-command"].loadString(::serverCommand, serverCommand)
            }
        }

        fun load() {
            node["height"].loadInt(::height, 1, 6)
            recipes.load()
            buttons.load()
            customButtons.load()
            background.load()
        }
    }

    fun load() {
        val file = fcPluginData.configFile
        Files.createDirectories(file.parent)

        config = if (Files.exists(file)) {
            modified = false
            newFile = false
            try {
                fcConfigFactory.load(file)
            } catch (e: Exception) {
                fcLogger.error("Error loading ${file.fileName}: ${e.message}")
                fcLogger.info("Using default configuration")
                return
            }
        } else {
            modified = true
            newFile = true
            fcConfigFactory.create()
        }

        if (config.headerComment != header) {
            config.headerComment = header
            modified = true
        }

        disableRecipes.load()
        recipeCalculations.load()
        playerDefaults.load()
        layout.load()

        if (modified) {
            config.save(file)
        }

        if (newFile) {
            fcLogger.info("Created ${file.fileName}")
        }

        modified = false
        newFile = false
    }

    init {
        load()
    }

    private inline fun <T> FcConfigNode.modify(
        newValue: T,
        message: (newValue: T) -> String = { "Set value to $it" },
    ): T {
        if (!newFile) {
            fcLogger.info("${fcPluginData.configFile.fileName} [$path]: ${message(newValue)}")
        }

        set(newValue)
        modified = true
        return newValue
    }

    private fun FcConfigNode.logErr(message: String) {
        fcLogger.error("${fcPluginData.configFile.fileName} [$path]: $message")
    }

    private fun FcConfigNode.loadInt(
        property: KMutableProperty0<Int>,
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE,
        default: Int = property.get(),
    ) {
        when (val newValue = getInt()) {
            null -> modify(default.coerceIn(min, max))
            !in min..max -> {
                property.set(default)
                logErr("$newValue is not in $min..$max. Defaulting to ${default}.")
            }
            else -> property.set(newValue)
        }
    }

    private fun FcConfigNode.loadDouble(
        property: KMutableProperty0<Double>,
        min: Double = Double.NEGATIVE_INFINITY,
        max: Double = Double.POSITIVE_INFINITY,
        default: Double = property.get(),
    ) {
        when (val newValue = getDouble()) {
            null -> modify(default)
            !in min..max -> {
                property.set(default)
                logErr("$newValue is not in $min..$max. Defaulting to ${default}.")
            }
            else -> property.set(newValue)
        }
    }

    private fun FcConfigNode.loadBoolean(
        property: KMutableProperty0<Boolean>,
        default: Boolean = property.get(),
    ) {
        when (val newValue = getBoolean()) {
            null -> property.set(modify(default))
            else -> property.set(newValue)
        }
    }

    private fun FcConfigNode.loadString(
        property: KMutableProperty0<String?>,
        default: String? = property.get(),
    ) {
        when (val newValue = getString()) {
            null -> if (default != null) property.set(modify(default))
            else -> property.set(newValue)
        }
    }

    private fun FcConfigNode.loadItem(
        itemProperty: KMutableProperty0<FcItemStack>,
        itemIdProperty: KMutableProperty0<String>,
        default: String = itemIdProperty.get(),
    ) {
        when (val newItemId = getString()) {
            null -> modify(default)
            else -> when (val newItem = fcItemStackFactory.parseOrNull(newItemId)) {
                null -> logErr("Invalid item id: $newItemId. Defaulting to $default.")
                else -> {
                    itemIdProperty.set(newItemId)
                    itemProperty.set(newItem)
                }
            }
        }
    }

    private fun FcConfigNode.loadExprList(
        property: KMutableProperty0<List<Expr>>,
        default: List<Expr> = property.get(),
    ) {
        when (val newDisableRecipes = getStringList()) {
            null -> modify(default.map { it.expression })
            else -> {
                val nonNulls = newDisableRecipes.filterNotNull()
                if (nonNulls.size != newDisableRecipes.size) {
                    modify(nonNulls) { "Removed null entries" }
                }
                property.set(nonNulls.map { Expr(it) })
            }
        }
    }
}
