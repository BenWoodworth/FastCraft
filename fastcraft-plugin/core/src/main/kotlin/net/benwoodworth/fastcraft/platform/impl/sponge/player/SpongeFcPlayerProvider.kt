package net.benwoodworth.fastcraft.implementations.sponge.player

import net.benwoodworth.fastcraft.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.player.FcPlayerProvider
import org.spongepowered.api.Sponge
import java.util.*

/**
 * Sponge implementation of [FcPlayerProvider].
 */
class SpongeFcPlayerProvider : FcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return Sponge.getServer().onlinePlayers
                .map(::SpongeFcPlayer)
    }

    override fun getOnlinePlayer(uuid: UUID): FcPlayer? {
        return Sponge.getServer().getPlayer(uuid)
                .map(::SpongeFcPlayer)
                .orElse(null)
    }
}
