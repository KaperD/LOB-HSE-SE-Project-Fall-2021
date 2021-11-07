package ru.hse.sport.football.team.service

import org.springframework.stereotype.Service
import ru.hse.sport.football.player.dao.PlayerDao
import ru.hse.sport.football.player.exception.PlayerNotFoundException
import ru.hse.sport.football.team.dao.TeamDao
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

@Service
class TeamService(
    private val playerDao: PlayerDao,
    private val teamDao: TeamDao
) {
    fun createTeam(teamDto: TeamDto): Team {
        checkPlayersExistence(teamDto.playersIds)
        return teamDao.save(teamDto)
    }

    fun getTeam(id: Int): Team? {
        return teamDao.get(id)
    }

    fun updateTeam(id: Int, updatedTeamDto: TeamDto): Team? {
        checkPlayersExistence(updatedTeamDto.playersIds)
        return teamDao.update(id, updatedTeamDto)
    }

    private fun checkPlayersExistence(playerIds: Set<Int>) {
        playerIds.stream().forEach {
            if (playerDao.getById(it) == null) {
                throw PlayerNotFoundException(it)
            }
        }
    }
}
