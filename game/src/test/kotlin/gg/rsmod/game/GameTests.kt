package gg.rsmod.game

import com.google.inject.Guice
import gg.rsmod.cache.FileSystem
import gg.rsmod.game.plugin.PluginEnvironment
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTests {

    @Test
    fun `construct with dependency injection`() {
        val module = GameModule()
        val guice = Guice.createInjector(module)

        val game = guice.getInstance(Game::class.java)
        assertNotNull(game)

        val fs = guice.getInstance(FileSystem::class.java)
        assertNotNull(fs)

        val plugins = guice.getInstance(PluginEnvironment::class.java)
        assertNotNull(plugins)
    }
}