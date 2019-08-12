package gg.rsmod.game

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Tom
 */
internal class ServerModule : AbstractModule() {

    override fun configure() {
        bind(CoroutineDispatcher::class.java).annotatedWith(Names.named("gameCoroutineDispatcher")).toInstance(Dispatchers.Default)
        bind(CoroutineDispatcher::class.java).annotatedWith(Names.named("ioCoroutineDispatcher")).toInstance(Dispatchers.IO)
    }

    private inline fun <reified T> bind(type: T) = bind(T::class.java) to type
}