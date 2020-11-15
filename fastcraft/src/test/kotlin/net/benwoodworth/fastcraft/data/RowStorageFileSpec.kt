package net.benwoodworth.fastcraft.data

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertFails

class RowStorageFileSpec : Spek({
    lateinit var filePath: Path

    val rowLength = 10
    val metadataLength = 20

    beforeGroup {
        filePath = Files.createTempFile("fastcraft-row-storage-file-", ".bin")
        Files.delete(filePath)
    }

    afterEachTest {
        if (Files.exists(filePath)) {
            Files.delete(filePath)
        }
    }

    describe("a row storage file") {
        describe("creating a new file") {
            it("should successfully create a new file") {
                RowStorageFile.create(filePath, rowLength, metadataLength)
            }

            it("should throw an exception if the file already exists") {
                assertFails {
                    Files.createFile(filePath)
                    RowStorageFile.create(filePath, rowLength, metadataLength)
                }
            }

            it("should be able to opened") {
                RowStorageFile.create(filePath, rowLength, metadataLength)
                RowStorageFile.open(filePath).close()
            }

            it("should start with no rows") {
                RowStorageFile.create(filePath, rowLength, metadataLength)
                RowStorageFile.open(filePath).use { file ->
                    assert(file.rowCount == 0L)
                }
            }

            it("should be created with no rows") {
                RowStorageFile.create(filePath, rowLength, metadataLength)
                RowStorageFile.open(filePath).use { file ->
                    assert(file.rowCount == 0L)
                }
            }

            it("should be created with metadata all be zero") {
                RowStorageFile.create(filePath, rowLength, metadataLength)
                RowStorageFile.open(filePath).use { file ->
                    val metadata = ByteArray(file.metadataLength)
                    file.readMetadata(metadata)
                    assert(metadata.all { it == 0.toByte() })
                }
            }
        }

        describe("opening an existing file") {
            it("should throw an exception if the file is invalid") {
                assertFails {
                    Files.createFile(filePath)

                    filePath.toFile().writer().use {
                        it.write("Hello, world!")
                    }

                    RowStorageFile.open(filePath).close()
                }
            }
        }

        describe("accessing an existing file") {
            lateinit var file: RowStorageFile

            beforeEachTest {
                RowStorageFile.create(filePath, rowLength, metadataLength)
                file = RowStorageFile.open(filePath)
            }

            afterEachTest {
                file.close()
            }

            it("should have the correct metadata length") {
                assert(file.metadataLength == metadataLength)
            }

            it("should have the correct row length") {
                assert(file.rowLength == rowLength)
            }

            it("adding rows should increase the row count") {
                val oldCount = file.rowCount
                val rowsToAdd = 5L
                val expected = oldCount + rowsToAdd

                file.addRows(rowsToAdd)
                val actual = file.rowCount

                assertEquals(expected, actual)
            }

            it("removing rows should decrease the row count") {
                file.addRows(10L)

                val oldCount = file.rowCount
                val rowsToRemove = 5L
                val expected = oldCount - rowsToRemove

                file.removeRows(rowsToRemove)
                val actual = file.rowCount

                assertEquals(expected, actual)
            }

            it("should write and read rows correctly") {
                val expected = listOf(
                    ByteArray(file.rowLength) { (0 + it).toByte() },
                    ByteArray(file.rowLength) { (1 + it).toByte() },
                    ByteArray(file.rowLength) { (2 + it).toByte() },
                    ByteArray(file.rowLength) { (3 + it).toByte() },
                    ByteArray(file.rowLength) { (4 + it).toByte() },
                )

                file.addRows(expected.size.toLong())
                for (row in 0L until file.rowCount) {
                    file.writeRow(row, expected[row.toInt()])
                }

                val actual = List(file.rowCount.toInt()) { row ->
                    ByteArray(file.rowLength)
                        .apply { file.readRow(row.toLong(), this) }
                }

                assertEquals(expected.size, actual.size)
                expected.forEachIndexed { i, bytes ->
                    assert(bytes.contentEquals(actual[i]))
                }
            }

            it("should write and read metadata correctly") {
                val expected = ByteArray(file.metadataLength) { (0 + it).toByte() }
                file.writeMetadata(expected)

                val actual = ByteArray(file.metadataLength)
                    .apply { file.readMetadata(this) }

                assert(expected.contentEquals(actual))
            }

            it("should write to a location in metadata correctly") {
                val bytes = ByteArray(1)

                for (i in 0 until file.metadataLength) {
                    bytes[0] = i.toByte()
                    file.writeMetadata(bytes, i)
                }

                val expected = ByteArray(file.metadataLength) { it.toByte() }

                val actual = ByteArray(file.metadataLength)
                    .apply { file.readMetadata(this) }

                assertEquals(actual.toList(), expected.toList())
            }

            it("should read from a location in metadata correctly") {
                val expected = ByteArray(file.metadataLength) { it.toByte() }
                file.writeMetadata(expected)

                val byte = ByteArray(1)
                val actual = ByteArray(file.metadataLength) {
                    file.readMetadata(byte, it)
                    byte[0]
                }

                assertEquals(actual.toList(), expected.toList())
            }

            it("should write to a location in a row correctly") {
                file.addRows(1L)
                val bytes = ByteArray(1)

                for (i in 0 until file.rowLength) {
                    bytes[0] = i.toByte()
                    file.writeRow(0L, bytes, i)
                }

                val expected = ByteArray(file.rowLength) { it.toByte() }

                val actual = ByteArray(file.rowLength)
                    .apply { file.readRow(0L, this) }

                assertEquals(actual.toList(), expected.toList())
            }

            it("should read from a location in a row correctly") {
                file.addRows(1L)
                val expected = ByteArray(file.rowLength) { it.toByte() }
                file.writeRow(0L, expected)

                val byte = ByteArray(1)
                val actual = ByteArray(file.rowLength) {
                    file.readRow(0L, byte, it)
                    byte[0]
                }

                assertEquals(actual.toList(), expected.toList())
            }
        }
    }
})
