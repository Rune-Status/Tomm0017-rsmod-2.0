package gg.rsmod.game.event.action

import gg.rsmod.game.event.Event

/**
 * @author Tom
 */
class ActionEvent<T>(
    val where: (T).() -> Boolean,
    val then: (T).() -> Any
) : Event