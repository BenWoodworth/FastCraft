package net.benwoodworth.fastcraft.core.util

/**
 * A FastCraft version.
 */
class FastCraftVersion : Comparable<FastCraftVersion> {

    val major: Int
    val minor: Int
    val patch: Int
    val label: String?
    val build: Int?

    /**
     * Construct a FastCraftVersion from its version parts.
     */
    constructor(
            major: Int,
            minor: Int,
            patch: Int = 0,
            label: String? = null,
            build: Int? = null
    ) {
        this.major = major
        this.minor = minor
        this.patch = patch
        this.label = label
        this.build = build
    }

    /**
     * Construct a [FastCraftVersion] given a version string.
     *
     * @param version the version string
     */
    constructor(version: String) {
        // [v]<MAJOR>.<MINOR>[.<PATCH>][-<LABEL>][-<BUILD>]
        val regex = Regex("^[v]?(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-([a-zA-Z]+))?(?:-(\\d+))?\$")

        val matches = regex.find(version) ?: throw IllegalArgumentException("Invalid FastCraft version: $version")

        val (major, minor, patch, label, build) = matches.destructured

        this.major = major.toInt()
        this.minor = minor.toInt()
        this.patch = patch.takeIf { it.isNotEmpty() }?.toInt() ?: 0
        this.label = label.takeIf { it.isNotEmpty() }
        this.build = build.takeIf { it.isNotEmpty() }?.toInt()
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

    override fun equals(other: Any?): Boolean {
        return other is FastCraftVersion
                && major == other.major
                && minor == other.minor
                && patch == other.patch
                && label == other.label
                && build == other.build
    }

    override fun hashCode(): Int {
        var result = 0
        result = result * 31 + major
        result = result * 31 + minor
        result = result * 31 + patch
        result = result * 31 + (label?.hashCode() ?: 0)
        result = result * 31 + (build?.hashCode() ?: 0)
        return result
    }
}
