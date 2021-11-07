package ru.hse.sport.football.player.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.hse.sport.football.player.exception.PlayerNotFoundException
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto
import ru.hse.sport.football.player.service.PlayerService

@RestController
@RequestMapping("/football/player")
class PlayerController(
    private val playerService: PlayerService
) {

    @PostMapping
    fun addNewPlayer(@Validated @RequestBody playerDto: PlayerDto): Player {
        return playerService.createPlayer(playerDto)
    }

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: Int): Player {
        return playerService.getPlayer(id) ?: throw PlayerNotFoundException(id)
    }

    @GetMapping("/all")
    fun getPlayer(): List<Player> {
        return playerService.getAllPlayers()
    }

    @PutMapping("/{id}")
    fun updatePlayer(
        @PathVariable id: Int,
        @Validated @RequestBody updatedPlayerDto: PlayerDto
    ): Player {
        return playerService.updatePlayer(id, updatedPlayerDto) ?: throw PlayerNotFoundException(id)
    }
}
