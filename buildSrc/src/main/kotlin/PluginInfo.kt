import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object PluginInfo {
    private const val baseVersion = "3.0.0-unknown"

    val id = "fastcraft"
    val name = "FastCraft"
    val version = getPluginVersion()
    val author = "Kepler"
    val description = "Redefines crafting in Minecraft"
    val website = "https://github.com/BenWoodworth/FastCraft"

    private fun getPluginVersion(): String {
        return try {
            getGitVersion()
        } catch (exception: Git.DescribeException) {
            System.err.println("Unable to get version using Git: ${exception.message}")
            System.err.println("Using a timestamp instead.")

            getUtcVersion()
        }
    }

    private fun getGitVersion(): String {
        Git.describe().run {
            val buildMetadata = mutableListOf<String>()

            if (commitsAhead > 0) {
                buildMetadata.add(commitsAhead.toString())
                buildMetadata.add(commitHash.take(8))
            }

            if (dirty) {
                buildMetadata.add("dirty")
            }

            if (broken) {
                buildMetadata.add("broken")
            }

            val metadataString = buildMetadata
                .takeIf { it.isNotEmpty() }
                ?.joinToString(".", prefix = "+")
                ?: ""

            return "$tag$metadataString"
        }
    }

    private fun getUtcVersion(): String {
        val time = DateTimeFormatter
            .ofPattern("yyyyMMddkkmmss")
            .withZone(ZoneId.of("UTC"))
            .format(Instant.now())

        return "$baseVersion+utc.$time"
    }
}