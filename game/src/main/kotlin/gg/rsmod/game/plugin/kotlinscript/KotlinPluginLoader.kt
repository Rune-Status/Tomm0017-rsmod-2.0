package gg.rsmod.game.plugin.kotlinscript

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import gg.rsmod.game.domain.PluginLoaderMessage
import gg.rsmod.game.plugin.PluginLoader
import io.github.classgraph.ClassGraph

/**
 * @author Tom
 */
class KotlinPluginLoader : PluginLoader<KotlinPlugin> {

    override fun load(): Result<Collection<KotlinPlugin>, PluginLoaderMessage> {
        val plugins = mutableListOf<KotlinPlugin>()

        val classGraph = ClassGraph().enableAllInfo()

        classGraph.scan().use { result ->
            val subclasses = result.getSubclasses(KotlinPlugin::class.java.name).directOnly()
            subclasses.forEach { subclass ->
                val pluginClass = subclass.loadClass(KotlinPlugin::class.java)
                val pluginConstructor = pluginClass.getConstructor()
                plugins.add(pluginConstructor.newInstance())
            }
        }

        return Ok(plugins)
    }
}