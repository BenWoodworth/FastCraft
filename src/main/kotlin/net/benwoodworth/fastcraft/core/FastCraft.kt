package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiButton
import net.benwoodworth.fastcraft.core.dependencies.server.TaskScheduler
import net.benwoodworth.fastcraft.core.dependencies.text.TextBuilder
import net.benwoodworth.fastcraft.core.dependencies.text.TextColorRegistry
import net.benwoodworth.fastcraft.impl.sponge.dependencies.item.SpongeItem
import net.benwoodworth.fastcraft.util.EventListener
import net.benwoodworth.fastcraft.util.Memento
import org.spongepowered.api.item.ItemTypes
import org.spongepowered.api.item.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Provider

/**
 * The core class of FastCraft.
 */
class FastCraft @Inject constructor(
        listenerPluginEnable: EventListener<EventPluginEnable>,
        listenerPluginDisable: EventListener<EventPluginDisable>,
        listenerPlayerJoin: EventListener<EventPlayerJoin>,

        //private val logger: Logger,
        private val taskScheduler: TaskScheduler,
        private val guiBuilderProvider: Provider<GuiBuilder>,
        private val textBuilderProvider: Provider<TextBuilder>,
        private val textColorRegistry: TextColorRegistry
) {

    init {
        listenerPluginEnable += this::onPluginEnable
        listenerPluginDisable += this::onPluginDisable
        listenerPlayerJoin += this::onPlayerJoin
    }

    fun onPluginEnable() {
        //logger.info("FastCraft core enabled")
    }

    fun onPluginDisable() {
        //logger.info("FastCraft core disabled")
    }

    fun onPlayerJoin(event: EventPlayerJoin) {
        event.player.sendMessage(textBuilderProvider.get()
                .text("Welcome to the server!")
                .build()
        )

        //taskScheduler.sync(40L) {
            event.player.sendMessage(textBuilderProvider.get()
                    .text("Opening GUI...")
                    .build()
            )

            val titleText = textBuilderProvider.get()
                    .text("Hello, ${event.player.username}!")
                    .color(textColorRegistry.gold)
                    .bold(true)
                    .build()

            val gui = guiBuilderProvider.get()
                    .setHeight(5)
                    .setTitle(titleText)
                    .build()

            val button = GuiButton.Impl()
            val buttonItem = ItemStack.builder()
                    .itemType(ItemTypes.CRAFTING_TABLE)
                    .build()
            button.item = Memento(SpongeItem(buttonItem))
            gui.setButton(1, 1, button)

            gui.open(event.player)
        //}
    }
}
