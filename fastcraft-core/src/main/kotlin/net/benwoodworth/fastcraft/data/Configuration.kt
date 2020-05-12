package net.benwoodworth.fastcraft.data

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import kotlin.reflect.KProperty

abstract class Configuration {
    private val children: MutableList<Pair<String, Any>> = mutableListOf()

    protected fun <T : Any> entry(key: String, default: () -> T, getEntry: FcConfigNode.() -> T?): Entry<T> {
        val entry = Entry(default, getEntry)
        children += key to entry
        return entry
    }

    protected fun <T : Any> entry(key: String, default: T, getEntry: FcConfigNode.() -> T?): Entry<T> {
        return entry(key, { default }, getEntry)
    }

    protected fun <T : Configuration> section(key: String, section: T): T {
        children += key to section
        return section
    }

    protected class Entry<T : Any>(
        private val default: () -> T,
        private val getEntry: FcConfigNode.() -> T?,
    ) {
        lateinit var value: T

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value
        }

        fun load(node: FcConfigNode) {
            value = node.getEntry() ?: default().also { node.set(it) }
        }
    }

    protected fun load(config: FcConfig) {
        children.forEach { (key, child) ->
            when (child) {
                is Configuration -> child.load(config[key])
                is Entry<*> -> child.load(config[key])
            }
        }
    }

    private fun load(node: FcConfigNode) {
        children.forEach { (key, child) ->
            when (child) {
                is Configuration -> child.load(node[key])
                is Entry<*> -> child.load(node[key])
            }
        }
    }
}
