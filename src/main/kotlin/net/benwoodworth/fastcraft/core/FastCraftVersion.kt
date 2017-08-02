package net.benwoodworth.fastcraft.core

/**
 * A FastCraft version.
 */
class FastCraftVersion(
        val major: Int,
        val minor: Int,
        val patch: Int,
        val label: String?,
        val build: Int?
) : Comparable<FastCraftVersion> {

    companion object {

        fun parse(version: String): FastCraftVersion {
            val regex = Regex("^(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-([a-zA-Z]+))?(?:-(\\d+))?\$")

            val matches = regex.find(version) ?: throw IllegalArgumentException("Invalid FastCraft version: $version")

            val (major, minor, patch, label, build) = matches.destructured
            return FastCraftVersion(
                    major.toInt(),
                    minor.toInt(),
                    patch.takeIf { it.isNotEmpty() }?.toInt() ?: 0,
                    label.takeIf { it.isNotEmpty() },
                    build.takeIf { it.isNotEmpty() }?.toInt()
            )
        }
    }

    override fun toString(): String {
        var result = "$major.$minor"

        if (patch != 0) {
            result += ".$patch"
        }

        if (label != null) {
            result += "-$label"
        }

        if (build != null) {
            result += "-$build"
        }

        return result
    }

    override fun compareTo(other: FastCraftVersion): Int {
        return when {
            major != other.major -> major - other.major
            minor != other.minor -> minor - other.minor
            patch != other.patch -> patch - other.patch
            build != null && other.build != null -> build - other.build
            else -> 0
        }
    }
}
