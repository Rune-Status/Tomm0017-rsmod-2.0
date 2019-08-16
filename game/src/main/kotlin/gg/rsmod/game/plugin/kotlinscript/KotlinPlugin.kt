package gg.rsmod.game.plugin.kotlinscript

import com.google.inject.Inject
import com.google.inject.Injector
import gg.rsmod.game.plugin.Plugin
import kotlin.script.experimental.annotations.KotlinScript

/**
 * A [Plugin] used as representation for KotlinScript plugin files.
 *
 * @author Tom
 */
@KotlinScript(
    displayName = "KotlinScript Plugin",
    fileExtension = "plugin.kts"
)
abstract class KotlinPlugin @Inject constructor(
    injector: Injector
) : Plugin(injector)