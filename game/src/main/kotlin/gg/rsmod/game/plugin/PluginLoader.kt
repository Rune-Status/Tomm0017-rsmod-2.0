package gg.rsmod.game.plugin

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import gg.rsmod.game.action.Action
import gg.rsmod.game.domain.PluginLoaderMessage
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * @author Tom
 */
interface PluginLoader<T : Plugin> {

    fun load(): Result<Collection<T>, PluginLoaderMessage>

    fun loadAsMap(): Result<Map<KClass<out Event>, List<Action<*>>>, PluginLoaderMessage> =
        load()
            .andThen { plugins ->
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

                return Ok(map)
            }
}