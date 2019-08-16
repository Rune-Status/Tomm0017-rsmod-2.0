package gg.rsmod.game.plugin

import com.google.inject.Injector
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * Base class that is responsible for loading [Plugin]s.
 *
 * @param T the type of [Plugin] that this loader is responsible
 * for fetching.
 *
 * @author Tom
 */
interface PluginLoader<T : Plugin> {

    /**
     * Get a collection of [Plugin]s.
     */
    fun getPlugins(injector: Injector): Collection<T>

    /**
     * Get the collection of [Plugin]s and return a map of all every [Action]
     * mapped by their respective [Event] type.
     */
    fun getMappedPlugins(injector: Injector): Map<KClass<out Event>, List<Action<*>>> {
        val map = mutableMapOf<KClass<out Event>, MutableList<Action<*>>>()
        val plugins = getPlugins(injector)
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