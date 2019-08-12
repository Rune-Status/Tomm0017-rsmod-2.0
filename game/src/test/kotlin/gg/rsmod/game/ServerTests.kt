package gg.rsmod.game

import com.google.inject.Guice
import gg.rsmod.Server
import gg.rsmod.ServerModule
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerTests {

    @Test
    fun `construct with dependency injection`() {
        val module = ServerModule()
        val guice = Guice.createInjector(module)

        val server = guice.getInstance(Server::class.java)
        assertNotNull(server)
    }
}