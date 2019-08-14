package gg.rsmod.game.plugin

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import gg.rsmod.game.action.Action
import gg.rsmod.game.event.Event
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventTests {

    @Test
    fun `trigger a good ('where' returns true) and bad ('where' returns false) event`() {
        val event = TestEvent()

        val goodEvent = Action<TestEvent>({ true }, { })
        assertTrue(goodEvent.where(event))

        val badEvent = Action<TestEvent>({ false }, { })
        assertFalse(badEvent.where(event))

        val environment = PluginEnvironment(mapOf(
            TestEvent::class to listOf(goodEvent, badEvent)
        ))

        val result = environment.trigger(event)
        assertNull(result.getError())

        val triggeredEvents = result.get() as? List<Action<*>>
        assertNotNull(triggeredEvents)
        triggeredEvents!!

        // The amount of triggered events should be equal to 1
        // as only the 'good event' should've called it's `then`
        // function.
        assertTrue(triggeredEvents.size == 1)
    }

    private class TestEvent : Event
}