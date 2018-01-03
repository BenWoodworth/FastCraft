package net.benwoodworth.fastcraft.implementations.bukkit

import org.bukkit.Bukkit
import java.util.logging.Level

/**
 * A Bukkit API version.
 */
data class BukkitApiVersion( // TODO Test
        private val major: Int,
        private val minor: Int = 0,
        private val patch: Int = 0,
        private val rMajor: Int? = null,
        private val rMinor: Int? = null,
        private val pre: Int? = null
) : Comparable<BukkitApiVersion> {

    companion object {

        /**
         * The server's API version.
         */
        val current by lazy {
            val versionStr = Bukkit.getBukkitVersion()
            val version = BukkitApiVersion.parse(versionStr)

            version ?: run {
                BukkitFastCraft.logger.log(
                        Level.SEVERE,
                        "Unsupported Bukkit API version: $versionStr."
                                + "Assuming latest version. Please report this."
                )

                BukkitApiVersion(1000)
            }
        }

        /**
         * Parses the Bukkit version.
         *
         * @param version the version string
         * @return the parsed Bukkit version, or null if invalid
         */
        fun parse(version: String): BukkitApiVersion? {
            // [major].[minor].[patch?]-[revision?]-[pre?]-[...?]
            val regex = Regex("""^(\d+)\.(\d+)(?:\.(\d+))?(?:-R(\d+)\.(\d+))?(?:-pre(\d+))?(?:-.*)?$""")

            val match = regex.matchEntire(version) ?: return null
            val (major, minor, patch, rMajor, rMinor, pre) = match.destructured

            return BukkitApiVersion(
                    major.toInt(),
                    minor.toInt(),
                    if (patch.isEmpty()) 0 else patch.toInt(),
                    if (rMajor.isEmpty()) null else rMajor.toInt(),
                    if (rMinor.isEmpty()) null else rMinor.toInt(),
                    if (pre.isEmpty()) null else pre.toInt()
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

        return result
    }
}