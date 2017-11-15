package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.config.FcConfigLoader
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.abstractions.event.Listener
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.Gui
import net.benwoodworth.fastcraft.dependencies.abstractions.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.server.Task
import net.benwoodworth.fastcraft.dependencies.abstractions.text.Text
import net.benwoodworth.fastcraft.dependencies.abstractions.text.TextColorRegistry
import net.benwoodworth.fastcraft.dependencies.abstractions.text.TextStyle
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * The core class of FastCraft.
 */
@Singleton
class FastCraft @Inject constructor(
        listenerPluginEnable: Listener<EventPluginEnable>,
        listenerPluginDisable: Listener<EventPluginDisable>,
        listenerPlayerJoin: Listener<EventPlayerJoin>,

        private val fcConfigLoader: Provider<FcConfigLoader>,

        private val textBuilderProvider: Provider<Text.Builder>,
        private val guiBuilderProvider: Provider<Gui.Builder>,
        private val itemBuilderProvider: Provider<Item.Builder>,
        private val textColorRegistry: TextColorRegistry,
        private val taskBuilderProvider: Provider<Task.Builder>
) {

    init {
        listenerPluginEnable += this::onPluginEnable
        listenerPluginDisable += this::onPluginDisable
        listenerPlayerJoin += this::onPlayerJoin
    }

    fun onPluginEnable() {
        println("FASTCRAFT ENABLED")
    }

    fun onPluginDisable() {
        println("FASTCRAFT DISABLED")
    }

    fun onPlayerJoin(event: EventPlayerJoin) {
        event.player.sendMessage(textBuilderProvider.get()
                .text("FastCraft is up and running!")
                .build()
        )

        val guiText = textBuilderProvider.get()
                .text("FastCraft")
                .build()

        val gui = guiBuilderProvider.get()
                .height(6)
                .title(guiText)
                .build()

        val buttonItemText = textBuilderProvider.get()
                .text("This is a demo!")
                .textStyle(TextStyle(textColorRegistry.aqua, true))
                .build()

        val buttonItemLore = textBuilderProvider.get()
                .text("How does it look?")
                .textStyle(TextStyle(textColorRegistry.green, italic = true))
                .build()

        val buttonItem = itemBuilderProvider.get()
                .type("minecraft:diamond")
                .amount(42)
                .displayName(buttonItemText)
                .lore(buttonItemLore)
                .build()

        val button = GuiButton.Impl()
        button.item = buttonItem
        button.clickListener += { clickEvent ->
            val message = textBuilderProvider.get()
                    .text("You clicked a button in the GUI!!")
                    .build()

            clickEvent.player?.sendMessage(message)
        }

        gui.setButton(1, 1, button)
        taskBuilderProvider.get().run {
            gui.open(event.player)
        }
    }
}
