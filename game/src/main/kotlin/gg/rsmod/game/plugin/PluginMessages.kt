package gg.rsmod.game.plugin

sealed class PluginTriggerMessage(private val reason: String) {
    override fun toString(): String = reason
}

object PluginEventTypeNotFound : PluginTriggerMessage("Plugin event type not found")