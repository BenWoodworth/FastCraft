package net.benwoodworth.fastcraft.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import org.bukkit.Material
import javax.inject.Provider

@AutoFactory
@Suppress("ClassName")
class Bukkit_11300R01_FcItemType(
    val material: Material,

    @Provided private val textBuilder: Provider<FcTextBuilder>
) : FcItemType {

    override val id: String
        get() = with(material.key) { "$namespace:$key" }

    override val name: FcText
        get() = textBuilder.get()
            .translate(
                with(material.key) { "item.$namespace.$key" }
            )
            .build()
}
