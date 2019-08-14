package gg.rsmod.game.plugin

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PluginEventTriggerTests {

    @Test
    fun `trigger event`() {
        val action = Action<TestEvent>({ true }, {})

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(action)
        ))

        val triggered = environment.trigger(TestEvent())
        assertNull(triggered.getError())

        assertSame(1, triggered.get()?.size ?: -1)
    }

    @Test
    fun `trigger filtered event`() {
        val action = Action<TestEvent>({ false }, {})

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(action)
        ))

        val triggered = environment.trigger(TestEvent())
        assertNull(triggered.getError())

        assertSame(0, triggered.get()?.size ?: -1)
    }

    @Test
    fun `trigger event with two actions, but filter one of them`() {
        val triggerAction = Action<TestEvent>({ true }, {})
        val filterAction = Action<TestEvent>({ false }, {})

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(triggerAction, filterAction)
        ))

        val triggered = environment.trigger(TestEvent())
        assertNull(triggered.getError())

        // Only `triggerAction` should have been triggered, the other action
        // should have been filtered and not returned by `trigger` result.
        assertSame(1, triggered.get()?.size ?: -1)
    }

    @Test
    fun `bind and trigger action to wrong event type`() {
        val action = Action<Unit>({ true }, {})

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(action)
        ))

        // `action` uses `Unit` as its type, yet we used `TestEvent::class`
        // as its key when adding it to our `PluginEnvironment`. This leads
        // to a cast exception when trying to trigger the event.
        assertThrows<ClassCastException> { environment.trigger(TestEvent()) }
    }

    private class TestEvent : Event
}