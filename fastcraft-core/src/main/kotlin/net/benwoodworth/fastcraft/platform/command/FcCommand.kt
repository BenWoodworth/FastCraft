package net.benwoodworth.fastcraft.platform.command

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcCommand {
    fun process(source: FcCommandSource, args: String)

    fun testPermission(permission: String): Boolean {
        return true
    }

    fun getShortDescription(source: FcCommandSource): FcText? {
        return null
    }

    fun getHelp(source: FcCommandSource): FcText? {
        return null
    }

    fun getUsage(source: FcCommandSource): FcText? {
        return null
    }

    fun getSuggestions(source: FcCommandSource, args: String): List<String> {
        return emptyList()
    }
}
