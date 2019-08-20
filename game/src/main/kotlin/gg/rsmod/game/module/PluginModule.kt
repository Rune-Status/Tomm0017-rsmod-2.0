package gg.rsmod.game.module

import com.google.inject.AbstractModule
import gg.rsmod.game.plugin.PluginLoader
import gg.rsmod.game.plugin.kotlinscript.KotlinPluginLoader

/**
 * @author Tom
 */
class PluginModule : AbstractModule() {

    override fun configure() {
        bind(PluginLoader::class.java)
            .toInstance(KotlinPluginLoader())
    }
}