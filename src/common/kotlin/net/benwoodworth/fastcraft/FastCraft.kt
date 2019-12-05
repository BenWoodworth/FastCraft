package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.CraftingGuiFactory
import net.benwoodworth.fastcraft.platform.server.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcServer
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    serverListeners: FcServer,
    private val taskFactory: FcTaskFactory,
    private val craftingGuiFactory: CraftingGuiFactory,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors
) {
    init {
        serverListeners.onPlayerJoin += ::onPlayerJoin
    }

    fun disable() {
    }

    private fun onPlayerJoin(event: FcPlayerJoinEvent) {
        with(textFactory) {
            event.player.sendMessage(
                createFcText(
                    text = "Welcome to ",
                    color = textColors.aqua,
                    extra = listOf(
                        createFcText(
                            text = "the ",
                            color = textColors.green
                        ),
                        createFcText(
                            text = "server!",
                            bold = true
                        )
                    )
                )
            )
        }

        with(taskFactory) {
            val task = createFcTask(delaySeconds = 5.0) {
                craftingGuiFactory.openFastCraftGui(event.player)
            }

            task.schedule()
        }
    }
}
