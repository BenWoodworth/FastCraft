package net.benwoodworth.fastcraft.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.Logger
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.slf4j.Logger as SpongeLogger

/**
 * An adapter for Sponge loggers.
 */
class SpongeLoggerAdapter(
        logger: SpongeLogger
) : Logger, Adapter<SpongeLogger>(logger) {

    override fun logError(message: String) = base.error(message)

    override fun logInfo(message: String) = base.info(message)

    override fun logWarning(message: String) = base.warn(message)

    override fun logDebug(message: String) = base.debug(message)
}
