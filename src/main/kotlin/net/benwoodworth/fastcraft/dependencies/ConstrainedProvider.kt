package net.benwoodworth.fastcraft.dependencies

import javax.inject.Provider

/**
 * Provides a dependency, and checks whether it's
 * allowed under a certain constraint.
 */
class ConstrainedProvider<TDependency, in TConstraint>(
        private val provider: () -> TDependency,
        private val allowed: (TConstraint) -> Boolean = { true }
) : Provider<TDependency> {

    /**
     * Checks whether this dependency allowed under a certain constraint.
     *
     * @return `true` iff the dependency is allowed.
     */
    fun isAllowed(constraint: TConstraint) = allowed(constraint)

    override fun get() = provider()

    /**
     * Selects a dependency from a list of [ConstrainedProvider].
     */
    abstract class Selector<TDependency, out TConstraint>(
            private vararg val providers: ConstrainedProvider<TDependency, TConstraint>
    ) : Provider<TDependency> {

        /**
         * The constraint used to select a dependency.
         */
        protected abstract val constraint: TConstraint

        /**
         * Gets the first allowed dependency.
         */
        final override fun get() = providers
                .first { it.allowed(constraint) }
                .get()
    }
}