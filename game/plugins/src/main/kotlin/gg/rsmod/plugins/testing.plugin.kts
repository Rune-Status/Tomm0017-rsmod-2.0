package gg.rsmod.plugins

import gg.rsmod.game.event.message.ApproachNpc

on<ApproachNpc>()
    .where { target.name == "test" }
    .then {
        println("working")
    }