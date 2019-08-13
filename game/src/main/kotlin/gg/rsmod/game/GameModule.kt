package gg.rsmod.game

import com.github.michaelbull.result.mapBoth
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import gg.rsmod.cache.FileSystem
import gg.rsmod.cache.osrs.buildOsrs
import gg.rsmod.game.plugin.PluginSet
import gg.rsmod.game.type.objtype.ObjTypeList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Tom
 */
internal class GameModule : AbstractModule() {

    override fun configure() {
        bindInstance(CoroutineDispatcher::class.java, Names.named("gameCoroutineDispatcher"), Dispatchers.Default)
        bindInstance(CoroutineDispatcher::class.java, Names.named("ioCoroutineDispatcher"), Dispatchers.IO)

        FileSystem.buildOsrs("../data/js5").mapBoth(::configureFileSystem, ::error)

        bind<PluginSet>()
    }

    private fun configureFileSystem(fileSystem: FileSystem) {
        bindInstance(FileSystem::class.java, fileSystem)
        bind<ObjTypeList>()
    }

    private inline fun <reified T> bind() = bind(T::class.java)

    private inline fun <reified T> bindInstance(type: T) =
        bind(T::class.java).toInstance(type)

    private inline fun <reified K, reified T : K> bindInstance(clazz: Class<K>, type: T) =
        bind(clazz).toInstance(type)

    private inline fun <reified K, reified T : K> bindInstance(clazz: Class<K>, annotation: Annotation, type: T) =
        bind(clazz).annotatedWith(annotation).toInstance(type)
}