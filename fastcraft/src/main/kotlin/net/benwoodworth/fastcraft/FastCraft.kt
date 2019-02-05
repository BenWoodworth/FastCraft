package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcServerListeners
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import net.benwoodworth.fastcraft.platform.text.FcTextColors
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    private val logger: FcLogger,
    private val serverListeners: FcServerListeners,
    private val textFactory: FcTextFactory,
    private val textColors: FcTextColors,
    private val taskFactory: FcTaskFactory
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
        with(textFactory) {
            with(taskFactory) {
                val message = FcText(
                    text = "Hello, ",
                    color = textColors.blue,
                    extra = listOf(
                        FcText(
                            text = event.player.username,
                            color = textColors.green,
                            bold = true
                        ),
                        FcText(
                            text = "! How are you?"
                        )
                    )
                )

                val task = FcTask(delaySeconds = 5.0) {
                    event.player.sendMessage(message)
                }

                task.schedule()
            }
        }
    }
}
