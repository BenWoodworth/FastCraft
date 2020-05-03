package net.benwoodworth.fastcraft.bukkit.server

import org.bukkit.Server
import javax.inject.Inject

class BukkitFcServer_1_7 @Inject constructor(
    val server: Server,
) : BukkitFcServer {
    override val minecraftVersion: String =
        Regex("""MC:\s*([.\d]*)""")
            .find(server.getVersion())
            ?.run { groupValues[1] }
            ?: "unknown"
}
