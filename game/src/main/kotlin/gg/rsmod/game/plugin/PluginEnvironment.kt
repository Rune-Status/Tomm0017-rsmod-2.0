package gg.rsmod.game.plugin

import com.google.inject.Inject
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * Responsible for maintaining all the [Action]s and their [Event]s.
 *
 * @param actionEvents a map of all [Action]s and their [Event] listeners.
 *
 * @author Tom
 */
class PluginEnvironment @Inject constructor(
    val actionEvents: Map<KClass<out Event>, List<Action<*>>>
) {

    /**
     * Trigger [Action]s mapped to [Event] with type [T], only if
     * [Action.where] is met, otherwise it is filtered.
     *
     * @return a list of all the [Action]s that were not filtered and their
     * [Action.then] was invoked.
     */
    inline fun <reified T : Event> trigger(event: T): List<Action<T>> {
        val events = getEvents<T>()?.filter { it.where(event) } ?: return emptyList()
        events.forEach { it.then(event) }
        return events
    }

    /**
     * Get a list of all [Action]s bound to [Event] with type [T].
     *
     * @throws ClassCastException if an [Action] was constructed with
     * a type that does not match type [T].
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Event> getEvents(): List<Action<T>>? = actionEvents[T::class] as? List<Action<T>>
}