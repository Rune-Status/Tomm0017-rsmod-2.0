package gg.rsmod.game.domain

sealed class PluginTriggerMessage(private val reason: String) {

    override fun toString(): String = reason

    object EventTypeNotFound : PluginTriggerMessage("Plugin event type not found")
}