package net.benwoodworth.fastcraft.implementations.bukkit.api.text.spigot

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.util.Adapter
import net.md_5.bungee.api.chat.BaseComponent

class BukkitFcText_Spigot(
        override val base: BaseComponent
) : Adapter<BaseComponent>(), FcText {


}