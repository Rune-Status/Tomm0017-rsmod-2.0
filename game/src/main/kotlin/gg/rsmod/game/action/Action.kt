package gg.rsmod.game.action

/**
 * @author Tom
 */
class Action<T>(
    val where: (T).() -> Boolean,
    val then: (T).() -> Any
)