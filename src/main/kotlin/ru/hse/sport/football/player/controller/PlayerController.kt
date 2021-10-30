package ru.hse.sport.football.player.controller

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto
import ru.hse.sport.football.player.service.PlayerService

@RestController
@RequestMapping("/football/player")
class PlayerController(
    private val playerService: PlayerService
) {

    @PostMapping
    fun addNewPlayer(@Validated @RequestBody playerRequest: PlayerPostRequest): ResponseEntity<Player> {
        return ResponseEntity.ok(
            playerService.createPlayer(
                PlayerDto(
                    playerRequest.name,
                    playerRequest.country,
                    playerRequest.position,
                    playerRequest.height,
                    playerRequest.leadingFoot.toString(),
                    playerRequest.goals,
                    playerRequest.saves
                )
            )
        )
    }
}