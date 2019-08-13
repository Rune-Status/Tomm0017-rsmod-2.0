package gg.rsmod.game.event

/**
 * @author Tom
 */
class ActionEvent<T>(
    val where: (T).() -> Boolean,
    val then: (T).() -> Any
) : Event