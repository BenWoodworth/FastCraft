package net.benwoodworth.fastcraft.implementations.spigot.api.text

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.util.Adapter
import net.md_5.bungee.api.chat.BaseComponent

class SpigotFcText(
        override val base: BaseComponent
) : Adapter<BaseComponent>(), FcText {


}