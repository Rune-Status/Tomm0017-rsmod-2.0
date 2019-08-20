package gg.rsmod.game.module

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Tom
 */
class CoroutineModule : AbstractModule() {

    override fun configure() {
        bind(CoroutineDispatcher::class.java)
            .annotatedWith(Names.named("gameCoroutineDispatcher"))
            .toInstance(Dispatchers.Default)

        bind(CoroutineDispatcher::class.java)
            .annotatedWith(Names.named("ioCoroutineDispatcher"))
            .toInstance(Dispatchers.IO)
    }
}