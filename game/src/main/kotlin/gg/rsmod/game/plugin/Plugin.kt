package gg.rsmod.game.plugin

import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.reflect.KClass

/**
 * @author Tom
 */
open class Plugin {

    val events = mutableMapOf<KClass<out Event>, MutableList<Action<*>>>()

    inline fun <reified T : Event> on(): EventBuilder<T> =
        EventBuilder(events.computeIfAbsent(T::class) { mutableListOf() })

    @DslMarker
    annotation class EventBuilderMarker

    @EventBuilderMarker
    class EventBuilder<T>(private val events: MutableList<Action<*>>) {

        private var where: (T).() -> Boolean = { true }

        private lateinit var then: (T).() -> Unit

        fun where(where: (T).() -> Boolean): EventBuilder<T> {
            this.where = where
            return this
        }

        fun then(then: (T).() -> Unit) {
            this.then = then
            events.add(Action(where, then))
        }
    }
}