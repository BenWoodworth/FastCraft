package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.dependencies.config.Config

/**
 * Updates FastCraft's config files.
 */
class ConfigUpdater {

    fun update(plugin: Config, data: Config) {
        var configRevision = data.getInt("config-revision")

        for ((revision, updater) in updaters.toSortedMap()) {
            if (configRevision == null || configRevision < revision) {
                updater(plugin, data)
                configRevision = revision
            }
        }

        data.setInt("config-revision", configRevision!!)
    }

    private val updaters = mapOf<Int, (plugin: Config, data: Config) -> Unit>(
            0 to { plugin, data ->
                // TODO
            }
    )
}
