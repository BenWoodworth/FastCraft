package net.benwoodworth.fastcraft.dependencies

import javax.inject.Provider

/**
 * Provides the appropriate dependency for a specific API version.
 */
abstract class DependencyProvider<in TApiVersion : Comparable<TApiVersion>, TDependency>(
        private val apiVersion: TApiVersion,
        private vararg val dependencies: Pair<TApiVersion, () -> TDependency>
) : Provider<TDependency> {

    override fun get(): TDependency {
        return dependencies
                .firstOrNull { it.first >= apiVersion }
                ?.second?.invoke()
                ?: throw Exception("Unsupported API version: $apiVersion")
    }
}
