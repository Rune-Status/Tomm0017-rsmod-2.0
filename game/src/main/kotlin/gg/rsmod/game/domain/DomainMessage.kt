package gg.rsmod.game.domain

sealed class PluginTriggerMessage {
    /**
     * The plugin event type did not have any actions bound to it.
     */
    object EventTypeNotFound : PluginTriggerMessage()
}

sealed class PluginLoaderMessage(private val reason: String) {
}