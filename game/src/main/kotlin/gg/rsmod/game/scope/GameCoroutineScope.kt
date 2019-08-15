package gg.rsmod.game.scope

import com.google.inject.Inject
import com.google.inject.name.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

/**
 * A [CoroutineScope] where all game-thread jobs should be launched.
 *
 * @author Tom
 */
class GameCoroutineScope @Inject constructor(
    @Named("gameCoroutineDispatcher") override val coroutineContext: CoroutineDispatcher
) : CoroutineScope