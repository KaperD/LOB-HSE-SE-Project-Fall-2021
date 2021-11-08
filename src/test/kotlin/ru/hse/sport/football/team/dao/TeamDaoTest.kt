package ru.hse.sport.football.team.dao

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.hse.sport.football.SpringTest
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

class TeamDaoTest : SpringTest {
    @Autowired
    lateinit var teamDao: TeamDao

    val spartak = TeamDto(
        "Spartak",
        "Russia",
        "Premier",
        "Открытие банк Арена",
        "Лукойл",
        "Rui Vitória"
    )

    @Test
    fun `test save team`() {
        val team: Team = teamDao.save(spartak)
        val expectedTeam = Team(
            team.id,
            spartak.name,
            spartak.country,
            spartak.league,
            spartak.homeStadium,
            spartak.generalSponsor,
            spartak.coachName
        )
        assertEquals(expectedTeam, team)
    }

    @Test
    fun `test get number of teams`() {
        val before = teamDao.getNumberOfTeams()
        teamDao.save(spartak)
        assertEquals(before + 1, teamDao.getNumberOfTeams())
    }

    @Test
    fun `test get team`() {
        val team = teamDao.save(spartak)
        val gotTeam = teamDao.get(team.id)
        assertNotNull(gotTeam)
        assertEquals(team, gotTeam)

        assertNull(teamDao.get(-1))
    }

    @Test
    fun `test update team`() {
        val team = teamDao.save(spartak)
        val newTeamDto = TeamDto(
            "Spartak",
            "Russia",
            "Premier",
            "Открытие банк Арена",
            "Лукойл",
            "Rui Vitória"
        )
        val updatedTeam = teamDao.update(team.id, newTeamDto)
        val expectedTeam = Team(
            team.id,
            spartak.name,
            spartak.country,
            spartak.league,
            spartak.homeStadium,
            spartak.generalSponsor,
            spartak.coachName
        )
        assertEquals(expectedTeam, updatedTeam)
        assertEquals(expectedTeam, teamDao.get(team.id))

        assertNull(teamDao.update(-1, newTeamDto))
    }
}
