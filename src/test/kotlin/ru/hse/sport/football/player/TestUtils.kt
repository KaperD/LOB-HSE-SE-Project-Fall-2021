package ru.hse.sport.football.player

import org.junit.jupiter.api.Assertions.assertEquals
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

fun checkModelFitsDto(player: Player, playerDto: PlayerDto) {
    assertEquals(playerDto.name, player.name)
    assertEquals(playerDto.country, player.country)
    assertEquals(playerDto.position, player.position)
    assertEquals(playerDto.height, player.height)
    assertEquals(playerDto.leadingFoot, player.leadingFoot)
    assertEquals(playerDto.goals, player.goals)
    assertEquals(playerDto.saves, player.saves)
}

fun checkModelFitsDto(team: Team, teamDto: TeamDto) {
    assertEquals(teamDto.name, team.name)
    assertEquals(teamDto.country, team.country)
    assertEquals(teamDto.league, team.league)
    assertEquals(teamDto.homeStadium, team.homeStadium)
    assertEquals(teamDto.generalSponsor, team.generalSponsor)
    assertEquals(teamDto.coachName, team.coachName)
}
