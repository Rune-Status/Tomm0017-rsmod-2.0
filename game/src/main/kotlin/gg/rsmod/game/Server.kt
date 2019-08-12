package gg.rsmod.game

import com.google.inject.Inject
import gg.rsmod.game.coroutine.GameCoroutineScope
import gg.rsmod.game.coroutine.IoCoroutineScope

/**
 * @author Tom
 */
class Server @Inject constructor(
    val ioCoroutineScope: IoCoroutineScope,
    val gameCoroutineScope: GameCoroutineScope
) {

}