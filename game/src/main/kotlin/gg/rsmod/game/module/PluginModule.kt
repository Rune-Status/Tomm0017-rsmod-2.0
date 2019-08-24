package gg.rsmod.game.module

import com.google.inject.AbstractModule
import com.google.inject.Scope
import gg.rsmod.game.plugin.PluginLoader
import gg.rsmod.game.plugin.kotlinscript.KotlinPluginLoader

/**
 * @author Tom
 */
class PluginModule(
    private val scope: Scope
) : AbstractModule() {

    override fun configure() {
        bind(PluginLoader::class.java)
            .to(KotlinPluginLoader::class.java)
            .`in`(scope)
    }
}