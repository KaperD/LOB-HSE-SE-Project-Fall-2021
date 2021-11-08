package ru.hse.sport.football.team.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

@Component
class TeamDao {
    @Autowired
    private lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    private val insertSql = "insert into team(name, country, league, homeStadium, generalSponsor, coachName) " +
            "values (:name, :country, :league, :homeStadium, :generalSponsor, :coachName)"

    private val selectSql = "select * from team where id = :id"

    private val updateSql = "" +
            "update team set " +
            "name = :name, " +
            "country = :country, " +
            "league = :league, " +
            "homeStadium = :homeStadium, " +
            "generalSponsor = :generalSponsor, " +
            "coachName = :coachName " +
            "where id = :id"

    private val rowMapper =
        RowMapper<Team> { rs, _ ->
            Team(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("country"),
                rs.getString("league"),
                rs.getString("homeStadium"),
                rs.getString("generalSponsor"),
                rs.getString("coachName")
            )
        }

    fun getNumberOfTeams(): Int = namedParameterJdbcTemplate.queryForObject(
        "select count(*) from team",
        emptyMap<String, Any>(),
        Int::class.java
    )!!

    fun save(teamDto: TeamDto): Team {
        val namedParameters: SqlParameterSource = toParameters(teamDto)
        val keyHolder = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            insertSql,
            namedParameters,
            keyHolder,
            arrayOf("id")
        )
        return Team(
            keyHolder.key!!.toInt(),
            teamDto.name,
            teamDto.country,
            teamDto.league,
            teamDto.homeStadium,
            teamDto.generalSponsor,
            teamDto.coachName
        )
    }

    fun get(id: Int): Team? {
        return namedParameterJdbcTemplate.query(
            selectSql,
            MapSqlParameterSource().addValue("id", id),
            rowMapper
        ).firstOrNull()
    }

    fun update(id: Int, teamDto: TeamDto): Team? {
        val namedParameters: SqlParameterSource = toParameters(teamDto).addValue("id", id)
        val updated = namedParameterJdbcTemplate.update(
            updateSql,
            namedParameters
        )
        if (updated == 0) {
            return null
        }
        return Team(
            id,
            teamDto.name,
            teamDto.country,
            teamDto.league,
            teamDto.homeStadium,
            teamDto.generalSponsor,
            teamDto.coachName
        )
    }

    private fun toParameters(teamDto: TeamDto): MapSqlParameterSource {
        return MapSqlParameterSource()
            .addValue("name", teamDto.name)
            .addValue("country", teamDto.country)
            .addValue("league", teamDto.league)
            .addValue("homeStadium", teamDto.homeStadium)
            .addValue("generalSponsor", teamDto.generalSponsor)
            .addValue("coachName", teamDto.coachName)
    }
}
