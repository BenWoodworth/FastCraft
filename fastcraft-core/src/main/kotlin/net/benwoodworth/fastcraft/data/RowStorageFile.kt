package net.benwoodworth.fastcraft.data

import java.io.Closeable
import java.io.RandomAccessFile
import java.nio.file.Files
import java.nio.file.Path

/**
 * Row-based data storage file
 *
 * File Structure:
 * ```
 * | Offset           | Length | Description                                |
 * |------------------|--------|--------------------------------------------|
 * | 0x00             | 18     | Header: "FastCraft Storage\u0000" in UTF-8 |
 * | 0x12             | 2      | Row Length (RL, unsigned)                  |
 * | 0x14             | 2      | Metadata Length (ML, unsigned)             |
 * | 0x16             | ML     | Metadata                                   |
 * | 0x16 + ML + N*RL | RL     | Nth Row                                    |
 * ```
 */
class RowStorageFile private constructor(
    private val file: RandomAccessFile,
) : Closeable {
    companion object {
        private const val HEADER = "FastCraft Storage\u0000"
        private const val RL_ADDR = 0x14
        private const val RL_LEN = 2
        private const val ML_ADDR = 0x12
        private const val ML_LEN = 2

        /**
         * Creates a new `RowStorageFile`.
         *
         * @param file The file to create
         * @param rowLength the length of each row, in the range 1..2^16-1
         * @param metadataLength the storage file's metadata, with a maximum length of 2^16-1
         *
         * @throws FileAlreadyExistsException
         */
        fun create(file: Path, rowLength: Int, metadataLength: Int = 0) {
            require(rowLength in 0x01..0xFFFFFFFF) { "rowSize must be in the range 1..65535" }
            require(metadataLength in 0x00..0xFFFFFFFF) { "rowSize must be in the range 0..65535" }

            Files.createFile(file)

            RandomAccessFile(file.toFile(), "rw").use { f ->
                f.setLength((HEADER.length + RL_LEN + ML_LEN + metadataLength).toLong())
                f.write(HEADER.toByteArray(Charsets.UTF_8))
                f.writeShort(rowLength)
                f.writeShort(metadataLength)
                repeat(metadataLength) { f.writeByte(0) }
            }
        }

        /**
         * Opens an existing `RowStorageFile`.
         *
         * @param file The file to open
         *
         * @throws FileAlreadyExistsException
         */
        fun open(file: Path): RowStorageFile {
            return RowStorageFile(RandomAccessFile(file.toFile(), "rw"))
        }
    }

    val metadataLength: Int
    val rowLength: Int

    val rowCount: Long
        get() = (file.length() - getRowAddress(0)) / rowLength

    init {
        file.seek(0L)

        // Validate header
        val headerBytes = ByteArray(18)
        val headerBytesRead = file.read(headerBytes)
        if (headerBytesRead != HEADER.length) {
            throw FileFormatException("Invalid file header")
        }

        val header = headerBytes.toString(Charsets.UTF_8)
        if (header != HEADER) {
            throw FileFormatException("Invalid file header")
        }

        // Read row length
        val rowLengthBytes = ByteArray(RL_LEN)
        val rowLengthBytesRead = file.read(rowLengthBytes)
        rowLength = rowLengthBytes.toInt()
        if (rowLengthBytesRead != RL_LEN) {
            throw FileFormatException("Unexpected end of file")
        } else if (rowLength == 0) {
            throw FileFormatException("Row length cannot be zero")
        }

        // Validate metadata length
        val metadataLengthBytes = ByteArray(ML_LEN)
        val metadataLengthBytesRead = file.read(metadataLengthBytes)
        metadataLength = metadataLengthBytes.toInt()
        if (metadataLengthBytesRead != ML_LEN) {
            throw FileFormatException("Unexpected end of file")
        }

        val minLength = HEADER.length + RL_LEN + ML_LEN + metadataLength
        if (file.length() < minLength) {
            throw FileFormatException("Unexpected end of file")
        }

        // Validate row data size
        val rowRemainder = (file.length() - minLength) % rowLength
        if (rowRemainder != 0L) {
            throw FileFormatException("Row data size is not evenly divisible by row length")
        }
    }

    override fun close() {
        file.close()
    }

    fun readMetadata(bytes: ByteArray, position: Int = 0) {
        require(position >= 0) { "position must be non-negative" }
        require(bytes.size + position <= metadataLength) { "bytes are out of bounds" }

        file.seek((HEADER.length + RL_LEN + ML_LEN + position).toLong())
        file.read(bytes)
    }

    fun writeMetadata(bytes: ByteArray, position: Int = 0) {
        require(position >= 0) { "position must be non-negative" }
        require(bytes.size + position <= metadataLength) { "bytes are out of bounds" }

        file.seek((HEADER.length + RL_LEN + ML_LEN + position).toLong())
        file.write(bytes)
    }

    private fun getRowAddress(row: Long): Long {
        return HEADER.length + RL_LEN + ML_LEN + metadataLength + (row * rowLength)
    }

    private fun validateAndSeekRow(row: Long, bytes: ByteArray, position: Int) {
        require(row in 0 until rowCount) { "row is out of bounds" }
        require(position >= 0) { "position must be non-negative" }
        require(bytes.size + position <= rowLength) { "bytes are out of bounds" }

        file.seek(getRowAddress(row) + position)
    }

    fun readRow(row: Long, bytes: ByteArray, position: Int = 0) {
        validateAndSeekRow(row, bytes, position)
        file.read(bytes)
    }

    fun writeRow(row: Long, bytes: ByteArray, position: Int = 0) {
        validateAndSeekRow(row, bytes, position)
        file.write(bytes)
    }

    fun addRows(rows: Long) {
        require(rows >= 0) { "rows must be non-negative" }

        val firstNewRow = rowCount

        file.setLength(file.length() + (rows * rowLength))
        file.seek(getRowAddress(firstNewRow))

        for (i in 0L until (rows * rowLength)) {
            file.write(0)
        }
    }

    fun removeRows(rows: Long) {
        require(rows >= 0) { "rows must be non-negative" }
        require(rows <= rowCount) { "rows must be <= rowCount" }

        file.setLength(file.length() - (rows * rowLength))
    }
}
