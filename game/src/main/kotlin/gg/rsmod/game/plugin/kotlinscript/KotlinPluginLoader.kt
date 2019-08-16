package gg.rsmod.game.plugin.kotlinscript

import com.google.inject.Injector
import gg.rsmod.game.plugin.PluginLoader
import io.github.classgraph.ClassGraph

/**
 * A [PluginLoader] that looks for [KotlinPlugin] in our runtime classpath.
 *
 * @author Tom
 */
class KotlinPluginLoader : PluginLoader<KotlinPlugin> {

    override fun getPlugins(injector: Injector): Collection<KotlinPlugin> {
        val plugins = mutableListOf<KotlinPlugin>()

        val classGraph = ClassGraph().enableAllInfo()
        classGraph.scan().use { result ->
            val subclasses = result.getSubclasses(KotlinPlugin::class.java.name).directOnly()
            subclasses.forEach { subclass ->
                val pluginClass = subclass.loadClass(KotlinPlugin::class.java)
                val pluginConstructor = pluginClass.getConstructor(Injector::class.java)
                plugins.add(pluginConstructor.newInstance(injector))
            }
        }

        return plugins
    }
}