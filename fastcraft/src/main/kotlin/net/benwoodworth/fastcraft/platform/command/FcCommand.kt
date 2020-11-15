package net.benwoodworth.fastcraft.platform.command

interface FcCommand {
    val description: String
    val usage: String

    fun process(source: FcCommandSource, arguments: String)

    fun getSuggestions(source: FcCommandSource, arguments: String): List<String> {
        return emptyList()
    }
}
