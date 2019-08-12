package gg.rsmod

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Tom
 */
internal class ServerModule : AbstractModule() {

    override fun configure() {
        bindInstance(CoroutineDispatcher::class.java, Dispatchers.Default, Names.named("gameCoroutineDispatcher"))
        bindInstance(CoroutineDispatcher::class.java, Dispatchers.IO, Names.named("ioCoroutineDispatcher"))
    }

    private inline fun <reified T> bindInstance(type: T) =
        bind(T::class.java).toInstance(type)

    private inline fun <reified K, reified T : K> bindInstance(clazz: Class<K>, type: T, annotation: Annotation) =
        bind(clazz).annotatedWith(annotation).toInstance(type)
}