package gg.rsmod.game.plugin

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PluginEventTriggerTests {

    @Test
    fun `trigger event`() {
        val event = TestEvent()
        val action = Action<TestEvent>({ true }, {})

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(action)
        ))

        val triggered = environment.trigger(event)
        assertNull(triggered.getError())

        assertSame(1, triggered.get()?.size ?: -1)
    }

    @Test
    fun `trigger filtered event`() {
        val event = TestEvent()
        val action = Action<TestEvent>({ false }, {})

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(action)
        ))

        val triggered = environment.trigger(event)
        assertNull(triggered.getError())

        assertSame(0, triggered.get()?.size ?: -1)
    }

    private class TestEvent : Event
}