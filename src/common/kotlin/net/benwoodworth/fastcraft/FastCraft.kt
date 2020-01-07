package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.FastCraftGuiFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcServer
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    serverListeners: FcServer,
    private val taskFactory: FcTaskFactory,
    private val fastCraftGuiFactory: FastCraftGuiFactory,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors
) {
    init {
        serverListeners.onPlayerJoin += ::onPlayerJoin
    }

    fun disable() {
    }

    private fun onPlayerJoin(event: FcPlayerJoinEvent) {
        event.player.sendMessage(
            textFactory.createFcText(
                text = "Welcome to ",
                color = textColors.aqua,
                extra = listOf(
                    textFactory.createFcText(
                        text = "the ",
                        color = textColors.green
                    ),
                    textFactory.createFcText(
                        text = "server!",
                        bold = true
                    )
                )
            )
        )

        val task = taskFactory.createFcTask(delaySeconds = 5.0) {
            fastCraftGuiFactory
                .createFastCraftGui(event.player)
                .openGui()
        }
        task.schedule()
    }
}
