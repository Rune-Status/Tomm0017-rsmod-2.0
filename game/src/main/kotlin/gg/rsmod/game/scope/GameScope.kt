package gg.rsmod.game.scope

import com.google.inject.Key
import com.google.inject.Provider
import com.google.inject.Scope

/**
 * @author Tom
 */
class GameScope : Scope {

    private val instances = mutableMapOf<Key<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> scope(key: Key<T>, unscoped: Provider<T>): Provider<T> =
        Provider { instances.computeIfAbsent(key) { unscoped.get() } as T }
}