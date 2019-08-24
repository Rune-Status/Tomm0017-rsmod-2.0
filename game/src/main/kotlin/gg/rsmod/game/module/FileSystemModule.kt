package gg.rsmod.game.module

import com.github.michaelbull.result.get
import com.google.inject.AbstractModule
import com.google.inject.Provider
import com.google.inject.Scope
import gg.rsmod.cache.FileSystem
import gg.rsmod.cache.osrs.buildOsrs
import gg.rsmod.game.model.obj.ObjTypeList

/**
 * @author Tom
 */
class FileSystemModule(
    private val scope: Scope
) : AbstractModule() {

    override fun configure() {
        bind(FileSystem::class.java)
            .toProvider(FileSystemProvider::class.java)
            .`in`(scope)

        bind(ObjTypeList::class.java).`in`(scope)
    }

    private class FileSystemProvider : Provider<FileSystem> {
        override fun get(): FileSystem = FileSystem.buildOsrs("./data/js5").get()!!
    }
}