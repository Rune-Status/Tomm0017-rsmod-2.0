package gg.rsmod.game.plugin

import gg.rsmod.game.event.PluginEvent

/**
 * @author Tom
 */
class PluginSet {

    val events = mutableMapOf<Class<*>, MutableList<PluginEvent<*>>>()

    inline fun <reified T> register(event: PluginEvent<T>) {
        val plugins = events.computeIfAbsent(T::class.java) { mutableListOf() }
        plugins.add(event)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> getPlugins(type: Class<T>): MutableList<PluginEvent<T>> {
        return events[type] as MutableList<PluginEvent<T>>
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> trigger(type: T) {
        val plugins = events[T::class.java] as? List<PluginEvent<T>> ?: return
        plugins.filter { it.where(type) }.forEach { it.then(type) }
    }
}