package net.benwoodworth.fastcraft.platform.locale

import net.benwoodworth.fastcraft.util.Extensible

interface FcTranslatable : Extensible {

    fun translate(locale: FcLocale? = null): String?
}
