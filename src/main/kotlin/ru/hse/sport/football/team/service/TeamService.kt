package ru.hse.sport.football.team.service

import org.springframework.stereotype.Service
import ru.hse.sport.football.team.dao.TeamDao
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

@Service
class TeamService(
    private val teamDao: TeamDao
) {
    fun createTeam(teamDto: TeamDto): Team {
        return teamDao.save(teamDto)
    }

    fun getTeam(id: Int): Team? {
        return teamDao.get(id)
    }

    fun updateTeam(id: Int, updatedTeamDto: TeamDto): Team? {
        return teamDao.update(id, updatedTeamDto)
    }
}
