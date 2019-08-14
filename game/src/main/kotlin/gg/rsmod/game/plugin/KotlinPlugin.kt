package gg.rsmod.game.plugin

import gg.rsmod.game.plugin.Plugin
import kotlin.script.experimental.annotations.KotlinScript

/**
 * @author Tom
 */
@KotlinScript(
    displayName = "KotlinScript Plugin",
    fileExtension = "plugin.kts"
)
abstract class KotlinPlugin : Plugin()