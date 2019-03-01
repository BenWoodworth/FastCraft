package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.gui.FcGuiFactory
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.server.*
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    serverListeners: FcServer,
    private val logger: FcLogger,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val taskFactory: FcTaskFactory,
    private val guiFactory: FcGuiFactory,
    private val itemFactory: FcItemFactory,
    private val itemTypes: FcItemTypes
) {

    init {
        logger.info("FASTCRAFT INITIALIZED")

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
        with(textFactory) {
            val gui = guiFactory.openChestGui(
                player = player,
                height = 4,
                title = createFcText(
                    text = "This is a ",
                    extra = listOf(
                        createFcText(
                            text = "Test ",
                            color = textColors.blue,
                            extra = listOf(
                                createFcText(
                                    text = "GUI",
                                    color = textColors.green,
                                    underline = true
                                )
                            )
                        ),
                        createFcText("!!")
                    )
                )
            )

            val layout = gui.layout
            for (x in 0 until layout.width) {
                for (y in 0 until layout.height) {
                    val button = layout.getButton(x, y)

                    var item = itemFactory.createFcItem(
                        type = itemTypes.netherStar,
                        amount = x + y * layout.width + 1,
                        displayName = createFcText("($x, $y)"),
                        lore = listOf(
                            createFcText(
                                "Name: ",
                                extra = listOf(itemTypes.netherStar.name)
                            ),
                            createFcText(
                                "Description: ",
                                extra = listOf(itemTypes.netherStar.description)
                            )
                        )
                    )

                    button.setItem(item)

                    button.onClick {
                        with(layout.getButton(x, y)) {
                            item = itemFactory.createFcItem(
                                copy = item,
                                amount = item.amount % 63 + 1
                            )

                            setItem(item)
                        }
                    }
                }
            }

            gui.onClose {
                logger.info("GUI closed!")
            }
        }
    }
}
