package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.FcLogger
import net.benwoodworth.fastcraft.util.Adapter
import org.slf4j.Logger

/**
 * Sponge implementation of [FcLogger].
 */
class SpongeFcLogger(
        override val base: Logger
) : FcLogger, Adapter<Logger>() {

    override fun error(message: String) = base.error(message)

    override fun info(message: String) = base.info(message)

    override fun warn(message: String) = base.warn(message)
}
