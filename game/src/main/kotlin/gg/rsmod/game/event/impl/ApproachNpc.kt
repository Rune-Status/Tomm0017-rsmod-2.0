package gg.rsmod.game.event.impl

import gg.rsmod.game.event.Event
import gg.rsmod.game.model.npc.Npc

/**
 * @author Tom
 */
class ApproachNpc(
    val target: Npc,
    val operation: String
) : Event