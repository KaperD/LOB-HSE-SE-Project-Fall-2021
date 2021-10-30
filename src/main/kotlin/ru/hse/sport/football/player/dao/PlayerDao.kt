package ru.hse.sport.football.player.dao

import org.springframework.stereotype.Component
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto
import java.util.*
import kotlin.collections.ArrayList

@Component
class PlayerDao {
    private val playerList : ArrayList<Player> = arrayListOf()

    fun getNumberOfPlayers() = playerList.size

    fun save(playerDto: PlayerDto) : Player {
        playerList.add(
            Player(
                playerList.size,
                playerDto.name,
                playerDto.country,
                playerDto.position,
                playerDto.height,
                playerDto.leadingFoot,
                playerDto.goals,
                playerDto.saves
            )
        )
        return playerList.last()
    }

    fun getById(id: Int): Optional<Player> {
        return Optional.of(
            Player(
                0,
                "Danil",
                "Russia",
                "Goalkeeper",
                190,
                "Right",
                1,
                123
        ))
    }
}