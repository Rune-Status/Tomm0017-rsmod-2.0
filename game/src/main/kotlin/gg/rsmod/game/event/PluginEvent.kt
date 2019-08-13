package gg.rsmod.game.event

/**
 * @author Tom
 */
class PluginEvent<T>(val where: (T) -> Boolean, val then: (T) -> Any)