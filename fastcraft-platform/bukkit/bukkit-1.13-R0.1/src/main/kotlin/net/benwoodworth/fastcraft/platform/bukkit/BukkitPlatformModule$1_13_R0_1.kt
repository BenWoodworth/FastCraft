package net.benwoodworth.fastcraft.platform.bukkit

import net.benwoodworth.fastcraft.platform.api.config.FcConfigBuilder
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiBuilder
import net.benwoodworth.fastcraft.platform.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.api.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.api.player.FcPlayerProvider
import net.benwoodworth.fastcraft.platform.api.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.api.server.FcLogger
import net.benwoodworth.fastcraft.platform.api.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.api.text.FcTextBuilder
import net.benwoodworth.fastcraft.platform.bukkit.player.`BukkitFcPlayerProvider$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.server.`BukkitFcLogger$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.server.`BukkitFcTaskBuilder$1_13_R0_1`
import net.benwoodworth.fastcraft.platform.bukkit.text.`BukkitFcTextBuilder$1_13_R0_1`
import org.bukkit.plugin.Plugin
import java.nio.file.Path

@Suppress("ClassName")
class `BukkitPlatformModule$1_13_R0_1`(
    private val plugin: Plugin
) : BukkitPlatformModule {

    private val logger by lazy {
        `BukkitFcLogger$1_13_R0_1`(plugin.logger)
    }

    override fun getLogger(): FcLogger {
        return logger
    }

    override fun getConfigPath(): Path {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPluginDirectory(): Path {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createConfigBuilder(): FcConfigBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createGuiBuilder(): FcGuiBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createItemBuilder(): FcItemBuilder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemTypes(): FcItemTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayerProvider(): FcPlayerProvider {
        return `BukkitFcPlayerProvider$1_13_R0_1`
    }

    override fun getRecipeService(): FcRecipeService {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createTaskBuilder(): FcTaskBuilder {
        return `BukkitFcTaskBuilder$1_13_R0_1`(plugin)
    }

    override fun createTextBuilder(): FcTextBuilder {
        return `BukkitFcTextBuilder$1_13_R0_1`
    }
}
