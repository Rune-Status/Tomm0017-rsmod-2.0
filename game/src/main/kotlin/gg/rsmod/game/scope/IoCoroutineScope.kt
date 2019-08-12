package gg.rsmod.game.scope

import com.google.inject.Inject
import com.google.inject.name.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

/**
 * @author Tom
 */
class IoCoroutineScope @Inject constructor(
    @Named("ioCoroutineDispatcher") override val coroutineContext: CoroutineDispatcher
) : CoroutineScope