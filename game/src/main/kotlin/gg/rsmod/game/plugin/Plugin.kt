package gg.rsmod.game.plugin

import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * Represents a plugin that can configure one or more [Action]s bound to
 * a specific [Event] type.
 *
 * @author Tom
 */
open class Plugin {

    /**
     * The [Action]s and their [Event] type that this plugin has asked to listen to.
     */
    val events = mutableMapOf<KClass<out Event>, MutableList<Action<*>>>()

    /**
     * Add a listener for [Event]s with type [T].
     */
    inline fun <reified T : Event> on(): ActionBuilder<T> =
        ActionBuilder(events.computeIfAbsent(T::class) { mutableListOf() })

    @DslMarker
    annotation class EventBuilderMarker

    @EventBuilderMarker
    class ActionBuilder<T>(private val events: MutableList<Action<*>>) {

        private var where: (T).() -> Boolean = { true }

        private lateinit var then: (T).() -> Unit

        fun where(where: (T).() -> Boolean): ActionBuilder<T> {
            this.where = where
            return this
        }

        fun then(then: (T).() -> Unit) {
            this.then = then
            events.add(Action(where, then))
        }
    }
}