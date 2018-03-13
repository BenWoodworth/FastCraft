package net.benwoodworth.fastcraft.dependencies.api.text

interface FcTextClickEvent {

    interface Factory {
        fun openUrl(url: String): FcTextClickEvent
        fun openFile(file: String): FcTextClickEvent
        fun runCommand(command: String): FcTextClickEvent
        fun suggestCommand(command: String): FcTextClickEvent
        fun changePage(page: Int): FcTextClickEvent
    }
}