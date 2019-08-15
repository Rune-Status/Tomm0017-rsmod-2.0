package gg.rsmod.game.action

/**
 * An action that can store a condition that should be
 * met before invoking [then].
 *
 * @param where the condition that should be met to invoke [then].
 * @param then the action that should take place when the condition
 * of [where] is met.
 *
 * @author Tom
 */
class Action<T>(
    val where: (T).() -> Boolean,
    val then: (T).() -> Unit
)