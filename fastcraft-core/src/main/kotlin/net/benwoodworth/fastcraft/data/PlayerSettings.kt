package net.benwoodworth.fastcraft.data

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import java.io.Closeable
import java.nio.file.Files
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

@Singleton
class PlayerSettings @Inject constructor(
    fcPluginData: FcPluginData,
    fcPlayerOperations: FcPlayer.Operations,
    private val config: FastCraftConfig,
) : Closeable,
    FcPlayer.Operations by fcPlayerOperations {

    private companion object {
        val ROW_LEN = 17

        val ROW_UUID_LOC = 0
        val ROW_UUID_LEN = 16

        val ROW_FCENABLED_LOC = 16
        val ROW_FCENABLED_MASK = 0b11000000.toByte()
        val ROW_FCENABLED_ENABLED = 0b01000000.toByte()
        val ROW_FCENABLED_DISABLED = 0b10000000.toByte()
    }

    /**
     * Row format:
     * ```
     * 0-7:  Player UUID (most)
     * 8-15: Player UUID (least)
     * 16:   0bAAXXXXXX; AA = FcEnabled, X = Undefined
     * ```
     *
     * X: Undefined
     *
     * AA: FastCraft Enabled
     * ```
     * 01: Enabled
     * 10: Disabled
     * ```
     */
    private val file: RowStorageFile

    private val playerRows: MutableMap<UUID, Long> = hashMapOf()

    init {
        val prefsFilePath = fcPluginData.dataFolder.resolve("player-settings.dat")
        if (!Files.exists(prefsFilePath)) {
            Files.createDirectories(prefsFilePath.parent)
            RowStorageFile.create(prefsFilePath, ROW_LEN, 0)
        }

        file = RowStorageFile.open(prefsFilePath)
    }

    override fun close() {
        file.close()
        playerRows.clear()
    }

    private fun getPlayerRow(playerId: UUID): Long {
        playerRows[playerId]?.let { row -> return row }

        val playerIdBytes = playerId.toByteArray()
        val rowUuid = ByteArray(ROW_UUID_LEN)
        for (row in 0L until file.rowCount) {
            file.readRow(row, rowUuid, ROW_UUID_LOC)
            if (playerIdBytes.contentEquals(rowUuid)) {
                playerRows[playerId] = row
                return row
            }
        }

        file.addRows(1L)
        val row = file.rowCount - 1
        file.writeRow(row, playerIdBytes, ROW_UUID_LOC)
        playerRows[playerId] = row
        return row
    }

    fun UUID.toByteArray(): ByteArray {
        val result = ByteArray(16)

        val most = this.mostSignificantBits
        for (i in 0..7) {
            result[i] = (most shr (56 - 8 * i) and 0xFF).toByte()
        }

        val least = this.leastSignificantBits
        for (i in 8..15) {
            result[i] = (least shr (120 - 8 * i) and 0xFF).toByte()
        }

        return result
    }

    fun getFastCraftEnabled(player: FcPlayer): Boolean {
        return getFastCraftEnabled(player.uuid)
    }

    fun getFastCraftEnabled(playerId: UUID): Boolean {
        val row = getPlayerRow(playerId)
        val bytes = ByteArray(1)
        file.readRow(row, bytes, ROW_FCENABLED_LOC)

        return when (bytes[0] and ROW_FCENABLED_MASK) {
            ROW_FCENABLED_ENABLED -> true
            ROW_FCENABLED_DISABLED -> false
            else -> config.playerDefaults.enabled
        }
    }

    fun setFastCraftEnabled(player: FcPlayer, enabled: Boolean) {
        setFastCraftEnabled(player.uuid, enabled)
    }

    fun setFastCraftEnabled(playerId: UUID, enabled: Boolean) {
        val pref = when (enabled) {
            true -> ROW_FCENABLED_ENABLED
            false -> ROW_FCENABLED_DISABLED
        }

        val row = getPlayerRow(playerId)
        val bytes = ByteArray(1)
        file.readRow(row, bytes, ROW_FCENABLED_LOC)
        bytes[0] = bytes[0] and (ROW_FCENABLED_MASK.inv()) or pref
        file.writeRow(row, bytes, ROW_FCENABLED_LOC)
    }
}
