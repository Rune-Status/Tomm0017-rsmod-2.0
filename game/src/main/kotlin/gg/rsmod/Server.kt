package gg.rsmod

import com.google.inject.Inject
import gg.rsmod.game.scope.GameCoroutineScope
import gg.rsmod.game.scope.IoCoroutineScope

/**
 * @author Tom
 */
class Server @Inject constructor(
    val gameCoroutineScope: GameCoroutineScope,
    val ioCoroutineScope: IoCoroutineScope
) {

}