package gg.rsmod.game.plugin

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.google.inject.Inject
import gg.rsmod.game.event.Event
import gg.rsmod.game.event.action.ActionEvent

/**
 * @author Tom
 */
class PluginEnvironment @Inject constructor(
    val actionEvents: Map<Class<*>, List<ActionEvent<*>>>
) {

    inline fun <reified T : Event> trigger(event: T): Result<List<ActionEvent<T>>, PluginTriggerMessage> {
        val events = getEvents<T>() ?: return Err(PluginEventTypeNotFound)
        val filteredEvents = events.filter { it.where(event) }
        filteredEvents.forEach { it.then(event) }
        return Ok(filteredEvents)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> getEvents(): List<ActionEvent<T>>? = actionEvents[T::class.java] as? List<ActionEvent<T>>
}