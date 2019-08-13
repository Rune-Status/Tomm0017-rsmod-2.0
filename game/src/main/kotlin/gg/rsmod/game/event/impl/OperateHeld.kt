package gg.rsmod.game.event.impl

import gg.rsmod.game.event.Event

/**
 * @author Tom
 */
class OperateHeld(
    val id: Int,
    val slot: Int,
    val option: Int,
    val component: Int
) : Event