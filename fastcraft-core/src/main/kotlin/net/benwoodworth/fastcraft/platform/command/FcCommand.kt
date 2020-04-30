package net.benwoodworth.fastcraft.platform.command

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcCommand {
    val description: FcText?
        get() = null

    val usage: FcText?
        get() = null

    val permission: String?
        get() = null

    fun process(source: FcCommandSource, args: String)

    fun getSuggestions(source: FcCommandSource, args: String): List<String> {
        return emptyList()
    }
}
