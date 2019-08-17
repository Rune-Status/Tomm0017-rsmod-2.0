package gg.rsmod.game.plugin

import com.google.inject.Inject
import com.google.inject.Injector
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import kotlin.properties.ObservableProperty
import kotlin.reflect.KClass

/**
 * Represents a plugin that can configure one or more [Action]s bound to
 * a specific [Event] type.
 *
 * @param injector the [Injector] used for [Plugin.inject] - a solution
 * for exposing injected values to plugins.
 *
 * @author Tom
 */
open class Plugin @Inject constructor(
    val injector: Injector
) {

    /**
     * The [Action]s and their [Event] type that this plugin has asked to listen to.
     */
    val events = mutableMapOf<KClass<out Event>, MutableList<Action<*>>>()

    /**
     * Add a listener for [Event]s with type [T].
     */
    inline fun <reified T : Event> on(): ActionBuilder<T> =
        ActionBuilder(events.computeIfAbsent(T::class) { mutableListOf() })

    /**
     * Return an instance of the injected property with type [T].
     */
    inline fun <reified T> inject(): ObservableProperty<T> =
        InjectedProperty(injector.getInstance(T::class.java))

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

    class InjectedProperty<T>(value: T) : ObservableProperty<T>(value)
}