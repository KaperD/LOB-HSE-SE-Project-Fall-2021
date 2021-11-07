package ru.hse.sport.football.player

import org.junit.jupiter.api.Assertions
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

fun checkModelFitsDto(player: Player, playerDto: PlayerDto) {
    Assertions.assertEquals(playerDto.name, player.name)
    Assertions.assertEquals(playerDto.country, player.country)
    Assertions.assertEquals(playerDto.position, player.position)
    Assertions.assertEquals(playerDto.height, player.height)
    Assertions.assertEquals(playerDto.leadingFoot, player.leadingFoot)
    Assertions.assertEquals(playerDto.goals, player.goals)
    Assertions.assertEquals(playerDto.saves, player.saves)
}

fun checkModelFitsDto(team: Team, teamDto: TeamDto) {
    Assertions.assertEquals(teamDto.name, team.name)
    Assertions.assertEquals(teamDto.country, team.country)
    Assertions.assertEquals(teamDto.league, team.league)
    Assertions.assertEquals(teamDto.homeStadium, team.homeStadium)
    Assertions.assertEquals(teamDto.generalSponsor, team.generalSponsor)
    Assertions.assertEquals(teamDto.coachName, team.coachName)
    Assertions.assertEquals(teamDto.playersIds, team.playersIds)
}
