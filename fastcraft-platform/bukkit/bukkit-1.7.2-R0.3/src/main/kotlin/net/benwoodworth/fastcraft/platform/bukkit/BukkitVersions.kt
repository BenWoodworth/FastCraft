package net.benwoodworth.fastcraft.platform.bukkit

import org.bukkit.Server
import javax.inject.Inject

class BukkitVersions @Inject constructor(
    server: Server
) {

    private val obcRegex = Regex("org\\.bukkit\\.craftbukkit\\.(${Nms.regex})(?:\\..*)?")

    val apiString = server.bukkitVersion

    val api = try {
        Api.parse(apiString)
    } catch (exception: NumberFormatException) {
        null
    }

    val nmsString = obcRegex
        .matchEntire(server.javaClass.canonicalName)
        ?.run { groupValues[1] }


    val nms = nmsString?.let { Nms.parse(it) }

    data class Nms(
        private val major: Int,
        private val minor: Int,
        private val revision: Int
    ) : Comparable<Nms> {

        override fun compareTo(other: Nms) = when {
            major > other.major -> major - other.major
            minor > other.minor -> minor - other.minor
            else -> revision - other.revision
        }

        companion object {
            val regex = Regex("v(\\d+)_(\\d+)_R(\\d+)")

            fun parse(string: String): Nms {
                val (major, minor, revision) = regex
                    .matchEntire(string)?.destructured
                    ?: throw NumberFormatException("Invalid NMS version: $this")

                return Nms(
                    major.toInt(),
                    minor.toInt(),
                    revision.toInt()
                )
            }
        }
    }

    data class Api( // TODO Test
        private val major: Int,
        private val minor: Int,
        private val patch: Int = 0,
        private val rMajor: Int? = null,
        private val rMinor: Int? = null,
        private val pre: Int? = null
    ) : Comparable<Api> {

        override operator fun compareTo(other: Api) = when {
            major != other.major -> major - other.major
            minor != other.minor -> minor - other.minor
            patch != other.patch -> patch - other.patch

            rMajor != other.rMajor -> when {
                rMajor == null -> 1 // Released after
                other.rMajor == null -> -1 // Released before
                else -> rMajor - other.rMajor
            }

            rMinor != other.rMinor -> when {
                rMinor == null -> -1 // Released before
                other.rMinor == null -> 1 // Released after
                else -> rMinor - other.rMinor
            }

            pre != other.pre -> when {
                pre == null -> 1 // Released after
                other.pre == null -> -1 // Released before
                else -> pre - other.pre
            }

            else -> 0
        }

        companion object {
            val regex = Regex("(\\d+)\\.(\\d+)(?:\\.(\\d+))?(?:-R(\\d+)(?:\\.(\\d+))?)?(?:-pre(\\d+)).*")

            fun parse(string: String): Api {
                return regex.matchEntire(string)
                    ?.run {
                        Api(
                            groupValues[1].toInt(),
                            groupValues[2].toInt(),
                            groupValues[3].toIntOrNull() ?: 0,
                            groupValues[4].toIntOrNull(),
                            groupValues[5].toIntOrNull(),
                            groupValues[6].toIntOrNull()
                        )
                    } ?: throw NumberFormatException("Invalid Bukkit version: $string")
            }
        }
    }
}
