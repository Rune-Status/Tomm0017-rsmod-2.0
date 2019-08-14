package gg.rsmod.game.plugin

import com.github.michaelbull.result.Result
import gg.rsmod.game.domain.PluginLoaderMessage

/**
 * @author Tom
 */
interface PluginLoader<T> {

    fun load(): Result<Collection<T>, PluginLoaderMessage>
}