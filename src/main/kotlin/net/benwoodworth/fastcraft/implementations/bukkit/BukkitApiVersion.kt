package net.benwoodworth.fastcraft.implementations.bukkit

/**
 * A Bukkit API version.
 */
data class BukkitApiVersion( // TODO Test
        val major: Int,
        val minor: Int,
        val patch: Int,
        val rMajor: Int?,
        val rMinor: Int?,
        val pre: Int?
) : Comparable<BukkitApiVersion> {

    companion object {

        /**
         * Parses the Bukkit version.
         *
         * @param version the version string
         * @return the parsed Bukkit version, or null if invalid
         */
        fun parse(version: String): BukkitApiVersion? {
            // [major].[minor?].[patch?]-[revision?]-[pre?]-SNAPSHOT
            val regex = Regex("""^(\d+)(?:\.(\d+))?(?:\.(\d+))?(?:-R(\d+)\.(\d+))?(?:-pre(\d+))?-SNAPSHOT$""")

            val match = regex.matchEntire(version) ?: return null
            val (major, minor, patch, rMajor, rMinor, pre) = match.destructured

            return BukkitApiVersion(
                    major.toIntOrNull() ?: 0,
                    minor.toIntOrNull() ?: 0,
                    patch.toIntOrNull() ?: 0,
                    rMajor.toIntOrNull(),
                    rMinor.toIntOrNull(),
                    pre.toIntOrNull()
            )
        }
    }

    override operator fun compareTo(other: BukkitApiVersion) = when {
        major != other.major -> major - other.major
        minor != other.minor -> minor - other.minor
        patch != other.patch -> patch - other.patch

        rMajor != other.rMajor -> when {
            rMajor == null -> 1 // released after
            other.rMajor == null -> -1 // released before
            else -> rMajor - other.rMajor
        }

        rMinor != other.rMinor -> when {
            rMinor == null -> 1 // released after
            other.rMinor == null -> -1 // released before
            else -> rMinor - other.rMinor
        }

        pre != other.pre -> when {
            pre == null -> 1 // released after
            other.pre == null -> -1 // released before
            else -> pre - other.pre
        }

        else -> 0
    }
}