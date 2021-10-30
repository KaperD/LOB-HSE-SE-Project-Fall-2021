package ru.hse.sport.football.player.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

@RestController
@RequestMapping("/football/player")
class PlayerController {

    @PostMapping
    fun addNewPlayer(@RequestBody playerDto: PlayerDto): Player {
        return Player(0,"", "", "", -1, "", 0, 0)
    }
}