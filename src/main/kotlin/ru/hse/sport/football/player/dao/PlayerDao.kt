package ru.hse.sport.football.player.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

@Component
class PlayerDao {
    @Autowired
    private lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    private val insertSql = "insert into player(name, country, position, height, leadingFoot, goals, saves, teamId) " +
            "values (:name, :country, :position, :height, :leadingFoot, :goals, :saves, :teamId)"

    private val selectSql = "select * from player where id = :id"
    private val selectAllSql = "select * from player"

    private val updateSql = "" +
            "update player set " +
            "name = :name, " +
            "country = :country, " +
            "position = :position, " +
            "height = :height, " +
            "leadingFoot = :leadingFoot, " +
            "goals = :goals, " +
            "saves = :saves, " +
            "teamId = :teamId " +
            "where id = :id"

    private val rowMapper =
        RowMapper<Player> { rs, _ ->
            Player(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("country"),
                rs.getString("position"),
                rs.getInt("height"),
                rs.getString("leadingFoot"),
                rs.getInt("goals"),
                rs.getInt("saves"),
                rs.getObject("teamId") as? Int
            )
        }

    fun getNumberOfPlayers(): Int = namedParameterJdbcTemplate.queryForObject(
        "select count(*) from player",
        emptyMap<String, Any>(),
        Int::class.java
    )!!

    fun save(playerDto: PlayerDto): Player {
        val namedParameters: SqlParameterSource = toParameters(playerDto)
        val keyHolder = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            insertSql,
            namedParameters,
            keyHolder,
            arrayOf("id")
        )
        return Player(
            keyHolder.key!!.toInt(),
            playerDto.name,
            playerDto.country,
            playerDto.position,
            playerDto.height,
            playerDto.leadingFoot,
            playerDto.goals,
            playerDto.saves,
            playerDto.teamId
        )
    }

    fun getById(id: Int): Player? {
        return namedParameterJdbcTemplate.query(
            selectSql,
            MapSqlParameterSource().addValue("id", id),
            rowMapper
        ).firstOrNull()
    }

    fun update(id: Int, playerDto: PlayerDto): Player? {
        val namedParameters: SqlParameterSource = toParameters(playerDto).addValue("id", id)
        val updated = namedParameterJdbcTemplate.update(
            updateSql,
            namedParameters
        )
        if (updated == 0) {
            return null
        }
        return Player(
            id,
            playerDto.name,
            playerDto.country,
            playerDto.position,
            playerDto.height,
            playerDto.leadingFoot,
            playerDto.goals,
            playerDto.saves,
            playerDto.teamId
        )
    }

    fun getAll(): List<Player> {
        return namedParameterJdbcTemplate.query(
            selectAllSql,
            rowMapper
        )
    }

    private fun toParameters(playerDto: PlayerDto): MapSqlParameterSource {
        return MapSqlParameterSource()
            .addValue("name", playerDto.name)
            .addValue("country", playerDto.country)
            .addValue("position", playerDto.position)
            .addValue("height", playerDto.height)
            .addValue("leadingFoot", playerDto.leadingFoot)
            .addValue("goals", playerDto.goals)
            .addValue("saves", playerDto.saves)
            .addValue("teamId", playerDto.teamId)
    }
}
