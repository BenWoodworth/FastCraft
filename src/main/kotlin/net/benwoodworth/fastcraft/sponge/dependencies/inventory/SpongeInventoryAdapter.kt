package net.benwoodworth.fastcraft.sponge.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.Inventory
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.item.inventory.Inventory as SpongeInventory

/**
 * Created by ben on 6/17/17.
 */
class SpongeInventoryAdapter(
        baseInventory: SpongeInventory
) : Inventory, Adapter<SpongeInventory>(baseInventory) {

    override val title: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val viewers: List<Player>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val carrier: Any?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}
