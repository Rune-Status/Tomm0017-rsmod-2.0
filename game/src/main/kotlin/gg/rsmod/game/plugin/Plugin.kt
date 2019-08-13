package gg.rsmod.game.plugin

import gg.rsmod.game.event.ActionEvent
import gg.rsmod.game.event.Event

/**
 * @author Tom
 */
open class Plugin {

    val events = mutableListOf<ActionEvent<*>>()

    inline fun <reified T : Event> on(): EventBuilder<T> = EventBuilder(events)

    @DslMarker
    annotation class EventBuilderMarker

    @EventBuilderMarker
    class EventBuilder<T>(private val events: MutableList<ActionEvent<*>>) {

        private var where: (T).() -> Boolean = { true }

        private lateinit var then: (T).() -> Any

        fun where(where: (T).() -> Boolean): EventBuilder<T> {
            this.where = where
            return this
        }

        fun then(then: (T).() -> Any) {
            this.then = then
            events.add(ActionEvent(where, then))
        }
    }
}