package ru.hse.sport.football.player

import org.junit.jupiter.api.Assertions
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

fun checkModelFitsDto(player: Player, playerDto: PlayerDto) {
    Assertions.assertEquals(playerDto.name, player.name)
    Assertions.assertEquals(playerDto.country, player.country)
    Assertions.assertEquals(playerDto.position, player.position)
    Assertions.assertEquals(playerDto.height, player.height)
    Assertions.assertEquals(playerDto.leadingFoot, player.leadingFoot)
    Assertions.assertEquals(playerDto.goals, player.goals)
    Assertions.assertEquals(playerDto.saves, player.saves)
}
