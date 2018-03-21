package net.benwoodworth.fastcraft.implementations.bukkit.text.spigot

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.bukkit.BukkitFcText_Bukkit
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import javax.inject.Inject

class BukkitFcText_Spigot(
        private val textComponent: BaseComponent,
        delegate: BukkitFcText_Bukkit
) : BukkitFcText by delegate {

    class Factory @Inject constructor(
            private val builderFactory: BukkitFcText_Spigot_BuilderFactory
    ) : FcText.Factory {

        override fun buildText(text: String): FcText.Builder {
            return builderFactory.create(TextComponent(text))
        }
    }

    @AutoFactory
    class Builder(
            private val textComponent: BaseComponent,

            @Provided private val delegate: BukkitFcText_Bukkit.Builder
    ) : BukkitFcText.Builder by delegate {

        override fun build(): FcText {
            return BukkitFcText_Spigot(textComponent, delegate.build())
        }
    }

    class Converter @Inject constructor(
            delegate: BukkitFcText_Bukkit.Converter
    ) : BukkitFcText.Converter by delegate {

        fun toSpigotTextComponent(text: BukkitFcText_Spigot) {

        }
    }
}