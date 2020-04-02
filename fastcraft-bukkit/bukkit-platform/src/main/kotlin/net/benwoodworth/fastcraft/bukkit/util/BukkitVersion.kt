package net.benwoodworth.fastcraft.bukkit.util

data class BukkitVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val rMajor: Int,
    val rMinor: Int,
) : Comparable<BukkitVersion> {
    override fun compareTo(other: BukkitVersion): Int {
        return compareValuesBy(
            this,
            other,
            { it.major },
            { it.minor },
            { it.patch },
            { it.rMajor },
            { it.rMinor }
        )
    }

    override fun toString(): String {
        return "$major.$minor.$patch-R$rMajor.$rMinor"
    }

    companion object {
        private val versionRegex = Regex("""^(\d+)(?:\.(\d+)(?:\.(\d+))?)?(?:-R(\d+)(?:.(\d+))?)?(?:-.*)?$""")

        fun parseOrNull(version: String): BukkitVersion? {
            val match = versionRegex.matchEntire(version) ?: return null
            val (major, minor, patch, rMajor, rMinor) = match.destructured

            return BukkitVersion(
                major = major.toIntOrNull() ?: 0,
                minor = minor.toIntOrNull() ?: 0,
                patch = patch.toIntOrNull() ?: 0,
                rMajor = rMajor.toIntOrNull() ?: 0,
                rMinor = rMinor.toIntOrNull() ?: 0
            )
        }

        fun parse(version: String): BukkitVersion {
            return parseOrNull(version)
                ?: throw IllegalArgumentException("Unable to parse Bukkit version '$version'")
        }
    }
}