package gg.rsmod.game.plugin

import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * @author Tom
 */
interface PluginLoader<T : Plugin> {

    fun load(): Collection<T>

    fun loadAsMap(): Map<KClass<out Event>, List<Action<*>>> {
        val plugins = load()
        val map = mutableMapOf<KClass<out Event>, MutableList<Action<*>>>()
        plugins.forEach { plugin ->
            plugin.events.forEach { (type, actions) ->
                // Get or create the list of actions tied to the type
                // class in `events`.
                val mappedAction = map.computeIfAbsent(type) { mutableListOf() }
                // All the actions found inside `events` should have
                // the same type as `type`.
                actions.forEach { action -> mappedAction.add(action) }
            }
        }
        return map
    }
}