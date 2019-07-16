package gg.rsmod.game

import kotlinx.coroutines.Dispatchers

/**
 * @author Tom <rspsmods@gmail.com>
 */
class Server {

    val ioDispatcher = Dispatchers.IO

    val gameDispatcher = Dispatchers.Default
}