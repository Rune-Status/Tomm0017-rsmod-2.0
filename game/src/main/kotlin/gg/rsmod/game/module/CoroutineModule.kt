package gg.rsmod.game.module

import com.google.inject.AbstractModule
import com.google.inject.Provider
import com.google.inject.Scope
import com.google.inject.name.Names
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Tom
 */
class CoroutineModule(
    private val scope: Scope
) : AbstractModule() {

    override fun configure() {
        bind(CoroutineDispatcher::class.java)
            .annotatedWith(Names.named("gameCoroutineDispatcher"))
            .toProvider(CoroutineDispatcherProvider(Dispatchers.Default))
            .`in`(scope)

        bind(CoroutineDispatcher::class.java)
            .annotatedWith(Names.named("ioCoroutineDispatcher"))
            .toProvider(CoroutineDispatcherProvider(Dispatchers.IO))
            .`in`(scope)
    }

    private class CoroutineDispatcherProvider(
        private val dispatcher: CoroutineDispatcher
    ) : Provider<CoroutineDispatcher> {
        override fun get(): CoroutineDispatcher = dispatcher
    }
}