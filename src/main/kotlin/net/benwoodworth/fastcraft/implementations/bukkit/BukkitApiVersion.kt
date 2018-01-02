package net.benwoodworth.fastcraft.implementations.bukkit

/**
 * A Bukkit API version.
 */
data class BukkitApiVersion( // TODO Test
        private val major: Int,
        private val minor: Int,
        private val patch: Int,
        private val rMajor: Int?,
        private val rMinor: Int?,
        private val pre: Int?,
        private val snapshot: Boolean
) : Comparable<BukkitApiVersion> {

    companion object {

        /**
         * Parses the Bukkit version.
         *
         * @param version the version string
         * @return the parsed Bukkit version, or null if invalid
         */
        fun parse(version: String): BukkitApiVersion? {
            // [major].[minor?].[patch?]-[revision?]-[pre?]-[snapshot?]
            val regex = Regex(
                    """^(\d+)(?:\.(\d+))?(?:\.(\d+))?(?:-R(\d+)\.(\d+))?(?:-pre(\d+))?(?:-(SNAPSHOT))?$"""
            )

            val match = regex.matchEntire(version) ?: return null
            val (major, minor, patch, rMajor, rMinor, pre, snapshot) = match.destructured

            return BukkitApiVersion(
                    major.toIntOrNull() ?: 0,
                    minor.toIntOrNull() ?: 0,
                    patch.toIntOrNull() ?: 0,
                    rMajor.toIntOrNull(),
                    rMinor.toIntOrNull(),
                    pre.toIntOrNull(),
                    snapshot == "SNAPSHOT"
            )
        }
    }

    /**
     * Compares the release order of this version with another.
     */
    override operator fun compareTo(other: BukkitApiVersion) = when {
        major != other.major -> major - other.major
        minor != other.minor -> minor - other.minor
        patch != other.patch -> patch - other.patch

        rMajor != other.rMajor -> when {
            rMajor == null -> 1 // Released after
            other.rMajor == null -> -1 // Released before
            else -> rMajor - other.rMajor
        }

        rMinor != other.rMinor -> when {
            rMinor == null -> 1 // Released after
            other.rMinor == null -> -1 // Released before
            else -> rMinor - other.rMinor
        }

        pre != other.pre -> when {
            pre == null -> 1 // Released after
            other.pre == null -> -1 // Released before
            else -> pre - other.pre
        }

        snapshot != other.snapshot -> when {
            snapshot -> -1 // Released before
            else -> 1 // Released after
        }

        else -> 0
    }

    override fun toString(): String {
        var result = "$major.$minor"
        if (patch != 0) result += ".$minor"

        if (rMajor != null || rMinor != null) {
            result += "-R"
            if (rMajor != null) result += rMajor
            if (rMinor != null) result += ".$rMinor"
        }

        if (pre != null) result += "-pre$pre"

        if (snapshot) result += "-SNAPSHOT"

        return result
    }
}