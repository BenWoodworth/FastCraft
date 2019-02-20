package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.server.*
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    private val logger: FcLogger,
    private val serverListeners: FcServer,
    private val textFactory: FcTextFactory,
    private val legacyTextFactory: FcLegacyTextFactory,
    private val textColors: FcTextColors,
    private val taskFactory: FcTaskFactory,
    private val guiFactory: FcGuiFactory,
    private val itemFactory: FcItemFactory,
    private val itemTypes: FcItemTypes
) {

    init {
        logger.info("FASTCRAFT INITIALIZED")
    }

    fun enable() {
        logger.info("FASTCRAFT ENABLED")

        serverListeners.onPlayerJoin += ::onPlayerJoin
    }

    fun disable() {
        logger.info("FASTCRAFT DISABLED")
    }

    private fun onPlayerJoin(event: FcPlayerJoinEvent) {
        with(taskFactory) {
            val task = createFcTask(delaySeconds = 5.0) {
                sendWelcomeMessage(event.player)
                showTestGui(event.player)
            }

            task.schedule()
        }
    }

    private fun sendWelcomeMessage(player: FcPlayer) {
        with(textFactory) {
            val message = createFcText(
                text = "Hello, ",
                color = textColors.blue,
                extra = listOf(
                    createFcText(
                        text = player.username,
                        color = textColors.green,
                        bold = true
                    ),
                    createFcText(
                        text = "How are you?"
                    )
                )
            )

            player.sendMessage(message)
        }
    }

    private fun showTestGui(player: FcPlayer) {
        val gui = guiFactory.openChestGui(
            player = player,
            height = 4,
            title = legacyTextFactory.createFcLegacyText(
                locale = player.locale,
                text = textFactory.createFcText(
                    text = "Test ",
                    color = textColors.blue,
                    extra = listOf(
                        textFactory.createFcText(
                            text = "GUI",
                            color = textColors.green,
                            underline = true
                        )
                    )
                )
            )
        )

        val layout = gui.layout
        for (x in 0 until layout.width) {
            for (y in 0 until layout.height) {
                val button = layout.getButton(x, y)

                button.setItem(
                    itemFactory.createFcItem(
                        type = itemTypes.netherStar,
                        amount = x + y * layout.width,
                        displayName = legacyTextFactory.createFcLegacyText(
                            locale = player.locale,
                            text = textFactory.createFcText("($x, $y)")
                        )
                    )
                )

                button.onClick {
                    with(layout.getButton(x, y)) {
                        setItem(itemFactory.createFcItem(itemTypes.air))
                        onClick.removeHandlers()
                    }
                }
            }
        }
    }
}
