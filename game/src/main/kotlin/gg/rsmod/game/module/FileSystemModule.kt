package gg.rsmod.game.module

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.mapError
import com.google.inject.AbstractModule
import gg.rsmod.cache.FileSystem
import gg.rsmod.cache.osrs.buildOsrs
import gg.rsmod.game.model.obj.ObjTypeList

/**
 * @author Tom
 */
class FileSystemModule : AbstractModule() {

    override fun configure() {
        FileSystem.buildOsrs("./data/js5")
            .andThen(::configureFileSystem)
            .andThen(::configureTypeLists)
            .mapError(::error)
    }

    private fun configureFileSystem(fileSystem: FileSystem): Result<FileSystem, Nothing> {
        bind(FileSystem::class.java).toInstance(fileSystem)
        return Ok(fileSystem)
    }

    private fun configureTypeLists(fileSystem: FileSystem): Result<FileSystem, Nothing> {
        bind(ObjTypeList::class.java).toInstance(ObjTypeList())
        return Ok(fileSystem)
    }
}