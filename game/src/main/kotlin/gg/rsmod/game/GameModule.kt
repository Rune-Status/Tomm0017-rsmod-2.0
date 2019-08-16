package gg.rsmod.game

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import gg.rsmod.cache.FileSystem
import gg.rsmod.cache.osrs.buildOsrs
import gg.rsmod.game.model.obj.ObjTypeList
import gg.rsmod.game.plugin.PluginLoader
import gg.rsmod.game.plugin.kotlinscript.KotlinPluginLoader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Tom
 */
internal class GameModule : AbstractModule() {

    override fun configure() {
        // Bind core instances.
        bindInstance(CoroutineDispatcher::class.java, Names.named("gameCoroutineDispatcher"), Dispatchers.Default)
        bindInstance(CoroutineDispatcher::class.java, Names.named("ioCoroutineDispatcher"), Dispatchers.IO)

        // Bind file system instances.
        FileSystem.buildOsrs("./data/js5")
            .mapError { error(it) }
            .andThen { fileSystem ->
                bindInstance(FileSystem::class.java, fileSystem)
                Ok(Unit)
            }
        bindInstance(ObjTypeList())

        // Bind plugin instances.
        bindInstance(PluginLoader::class.java, KotlinPluginLoader())
    }

    private inline fun <reified T> bindInstance(type: T) =
        bind(T::class.java).toInstance(type)

    private inline fun <reified K, reified T : K> bindInstance(clazz: Class<K>, type: T) =
        bind(clazz).toInstance(type)

    private inline fun <reified K, reified T : K> bindInstance(clazz: Class<K>, annotation: Annotation, type: T) =
        bind(clazz).annotatedWith(annotation).toInstance(type)
}