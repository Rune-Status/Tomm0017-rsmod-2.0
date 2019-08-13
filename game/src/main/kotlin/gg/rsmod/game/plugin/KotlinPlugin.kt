package gg.rsmod.game.plugin

import com.google.inject.Inject
import gg.rsmod.game.event.PluginEvent
import kotlin.script.experimental.annotations.KotlinScript

/**
 * @author Tom
 */
@KotlinScript(
    displayName = "KotlinScript Plugin",
    fileExtension = "plugin.kts"
)
abstract class KotlinPlugin @Inject constructor(
    val pluginSet: PluginSet
) {

    inline fun <reified T> on(): EventBuilder<T> {
        return EventBuilder(pluginSet.getPlugins(T::class.java))
    }

    @DslMarker
    annotation class EventBuilderMarker

    @EventBuilderMarker
    class EventBuilder<T>(private val plugins: MutableList<PluginEvent<T>>) {

        private lateinit var where: (T) -> Boolean

        private lateinit var then: (T) -> Any

        fun where(where: (T) -> Boolean) {
            this.where = where
        }

        fun then(then: (T) -> Any) {
            this.then = then
            plugins.add(PluginEvent(where, then))
        }
    }
}