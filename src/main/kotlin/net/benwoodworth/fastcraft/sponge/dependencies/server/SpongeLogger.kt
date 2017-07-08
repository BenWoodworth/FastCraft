package net.benwoodworth.fastcraft.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.Logger
import net.benwoodworth.fastcraft.core.util.Adapter
import org.slf4j.Logger as Sponge_Logger

/**
 * An adapter for Sponge loggers.
 */
class SpongeLogger(
        logger: Sponge_Logger
) : Logger, Adapter<Sponge_Logger>(logger) {

    override fun error(message: String) = base.error(message)

    override fun info(message: String) = base.info(message)

    override fun warn(message: String) = base.warn(message)

    override fun debug(message: String) = base.debug(message)
}
