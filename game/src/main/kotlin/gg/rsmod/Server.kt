package gg.rsmod

import com.google.inject.Inject
import gg.rsmod.game.scope.GameCoroutineScope
import gg.rsmod.game.scope.IoCoroutineScope

/**
 * @author Tom
 */
class Server @Inject constructor(
    val ioCoroutineScope: IoCoroutineScope,
    val gameCoroutineScope: GameCoroutineScope
) {

}