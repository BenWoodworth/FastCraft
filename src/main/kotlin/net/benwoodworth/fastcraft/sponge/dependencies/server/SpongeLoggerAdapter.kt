package net.benwoodworth.fastcraft.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.Logger
import net.benwoodworth.fastcraft.core.util.Adapter
import org.slf4j.Logger as SpongeLogger

/**
 * An adapter for Sponge loggers.
 */
class SpongeLoggerAdapter(
        logger: SpongeLogger
) : Logger, Adapter<SpongeLogger>(logger) {

    override fun error(message: String) = base.error(message)

    override fun info(message: String) = base.info(message)

    override fun warn(message: String) = base.warn(message)

    override fun debug(message: String) = base.debug(message)
}
