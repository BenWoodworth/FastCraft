package net.benwoodworth.fastcraft.impl.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.Logger
import net.benwoodworth.fastcraft.util.Adapter
import org.slf4j.Logger as Sponge_Logger

/**
 * Sponge implementation of [Logger].
 */
class SpongeLogger(
        logger: Sponge_Logger
) : Logger, Adapter<Sponge_Logger>(logger) {

    override fun error(message: String) = base.error(message)

    override fun info(message: String) = base.info(message)

    override fun warn(message: String) = base.warn(message)
}
