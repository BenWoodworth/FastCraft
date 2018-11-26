package net.benwoodworth.fastcraft.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.locale.Bukkit_11300R01_FcTranslatable
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import org.bukkit.Material
import javax.inject.Provider

@AutoFactory
@Suppress("ClassName")
class Bukkit_11300R01_FcItemType(
    val material: Material,

    @Provided private val buildText: Provider<FcTextBuilder>
) : FcItemType {

    override val name: FcTranslatable
        get() = Bukkit_11300R01_FcTranslatable(
            with(material.key) { "item.$namespace.$key" },
            material.name // TODO
        )

    override val description: FcTranslatable
        get() = Bukkit_11300R01_FcTranslatable(
            with(material.key) { "item.$namespace.$key.description" },
            "None" // TODO
        )
}
