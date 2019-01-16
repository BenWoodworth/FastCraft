package net.benwoodworth.fastcraft.platform.locale

interface FcTranslatable {

    fun translate(locale: FcLocale? = null): String?
}
