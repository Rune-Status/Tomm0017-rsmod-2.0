package gg.rsmod.game.plugin

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import gg.rsmod.game.event.action.ActionEvent
import gg.rsmod.game.event.message.ApproachNpc
import gg.rsmod.game.model.npc.Npc
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventTests {

    @Test
    fun `test approach npc`() {
        val npc = Npc("test")
        val event = ApproachNpc(npc, "operation")

        val goodEvent = ActionEvent<ApproachNpc>({ target.name == npc.name }, { })
        assertTrue(goodEvent.where(event))

        val badEvent = ActionEvent<ApproachNpc>({ target.name == "bad" }, { })
        assertFalse(badEvent.where(event))

        val environment = PluginEnvironment(mapOf<Class<*>, List<ActionEvent<*>>>(ApproachNpc::class.java to listOf(goodEvent, badEvent)))

        val result = environment.trigger(event)
        assertNull(result.getError())

        val triggeredEvents = result.get() as? List<ActionEvent<*>>
        assertNotNull(triggeredEvents)
        triggeredEvents!!

        // The amount of triggered events should be equal to 1
        // as only the 'good event' should've called it's `then`
        // function.
        assertTrue(triggeredEvents.size == 1)
    }
}