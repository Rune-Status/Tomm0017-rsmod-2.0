package gg.rsmod.game.plugin

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.google.inject.Inject
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * @author Tom
 */
class PluginEnvironment @Inject constructor(
    val actionEvents: Map<KClass<*>, List<Action<*>>>
) {

    inline fun <reified T : Event> trigger(event: T): Result<List<Action<T>>, PluginTriggerMessage> {
        val events = getEvents<T>()?.filter { it.where(event) } ?: return Err(PluginEventTypeNotFound)
        events.forEach { it.then(event) }
        return Ok(events)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> getEvents(): List<Action<T>>? = actionEvents[T::class] as? List<Action<T>>
}