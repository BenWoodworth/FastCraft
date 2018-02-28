package net.benwoodworth.fastcraft.implementations.bukkit

data class BukkitNmsVersion(
        val major: Int,
        val minor: Int,
        val revision: Int
) : Comparable<BukkitNmsVersion> {

    companion object {
        /**
         * Parses the Bukkit NMS version.
         *
         * @param version the version string.
         * @return the parsed Bukkit NMS version, or null if invalid.
         */
        fun parse(version: String): BukkitNmsVersion? {
            // [major].[minor]_R[revision]
            val regex = Regex("(\\d+)\\.(\\d+)_R(\\d+)")

            val match = regex.matchEntire(version) ?: return null
            val (major, minor, revision) = match.destructured

            return BukkitNmsVersion(
                    major.toInt(),
                    minor.toInt(),
                    revision.toInt()
            )
        }
    }

    override fun compareTo(other: BukkitNmsVersion) = when {
        major > other.major -> major - other.major
        minor > other.minor -> minor - other.minor
        else -> revision - other.revision
    }
}