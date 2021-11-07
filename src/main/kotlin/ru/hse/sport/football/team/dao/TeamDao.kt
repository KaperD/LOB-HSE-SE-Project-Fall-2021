package ru.hse.sport.football.team.dao

import org.springframework.stereotype.Component
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto
import java.util.concurrent.atomic.AtomicInteger

@Component
class TeamDao {
    private val teams: MutableMap<Int, Team> = mutableMapOf()
    private val currentId = AtomicInteger(1)

    fun getNumberOfTeams() = teams.size

    fun save(teamDto: TeamDto): Team {
        val team = Team(
            getUniqueId(),
            teamDto.name,
            teamDto.country,
            teamDto.league,
            teamDto.homeStadium,
            teamDto.generalSponsor,
            teamDto.coachName,
            teamDto.playersIds
        )
        teams[team.id] = team
        return team
    }

    fun get(id: Int): Team? {
        return teams[id]
    }

    fun update(id: Int, teamDto: TeamDto): Team? {
        if (!teams.containsKey(id)) {
            return null
        }
        val team = Team(
            id,
            teamDto.name,
            teamDto.country,
            teamDto.league,
            teamDto.homeStadium,
            teamDto.generalSponsor,
            teamDto.coachName,
            teamDto.playersIds
        )
        teams[id] = team
        return team
    }

    private fun getUniqueId(): Int {
        return currentId.getAndIncrement()
    }
}