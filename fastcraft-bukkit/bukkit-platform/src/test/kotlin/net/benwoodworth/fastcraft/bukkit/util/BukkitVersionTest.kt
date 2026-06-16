package net.benwoodworth.fastcraft.bukkit.util

import kotlin.test.Test
import kotlin.test.assertEquals

class BukkitVersionTest {
    @Test
    fun parse_plain_minecraft_versions() {
        assertEquals(
            BukkitVersion(1, 21, 8, 0, 0),
            BukkitVersion.parse("1.21.8")
        )
        assertEquals(
            BukkitVersion(1, 12, 2, 0, 0),
            BukkitVersion.parse("1.12.2")
        )
    }

    @Test
    fun parse_paper_build_version() {
        assertEquals(
            BukkitVersion(26, 1, 2, 0, 0),
            BukkitVersion.parse("26.1.2.build.2591-stable")
        )
    }

    @Test
    fun parse_legacy_bukkit_release_version() {
        assertEquals(
            BukkitVersion(1, 8, 0, 0, 1),
            BukkitVersion.parse("1.8-R0.1-SNAPSHOT")
        )
    }
}
