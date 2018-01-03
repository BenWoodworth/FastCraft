package net.benwoodworth.fastcraft.implementations.bukkit

class UnsupportedBukkitApiException(
        version: BukkitApiVersion
) : Exception("Unsupported Bukkit API version: $version") {

    /**
     * Uses the current Bukkit API version.
     */
    constructor() : this(BukkitApiVersion.current)
}
