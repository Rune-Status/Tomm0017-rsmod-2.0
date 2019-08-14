package gg.rsmod.game.plugin

import com.google.inject.Inject
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * @author Tom
 */
class PluginEnvironment @Inject constructor(
    val actionEvents: Map<KClass<out Event>, List<Action<*>>>
) {

    inline fun <reified T : Event> trigger(event: T): List<Action<T>> {
        val events = getEvents<T>()?.filter { it.where(event) } ?: return emptyList()
        events.forEach { it.then(event) }
        return events
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Event> getEvents(): List<Action<T>>? = actionEvents[T::class] as? List<Action<T>>
}