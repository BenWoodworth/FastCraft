package net.benwoodworth.fastcraft.sponge.server

import org.slf4j.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcLogger_Sponge_7 @Inject constructor(
    private val logger: Logger,
) : FcLogger_Sponge {
    override fun error(error: String): Unit = logger.error(error)

    override fun info(info: String): Unit = logger.info(info)

    override fun warn(warning: String): Unit = logger.warn(warning)
}
