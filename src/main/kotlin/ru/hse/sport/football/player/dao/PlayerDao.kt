package ru.hse.sport.football.player.dao

import org.springframework.stereotype.Component
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto
import kotlin.collections.ArrayList

@Component
class PlayerDao {
    private val playerList : ArrayList<Player> = arrayListOf()

    fun getNumberOfPlayers() = playerList.size

    fun save(playerDto: PlayerDto): Player {
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

    fun getById(id: Int): Player? {
        if (id < 0 || id >= playerList.size) {
            return null
        }
        return playerList[id]
    }

    fun update(id: Int, playerDto: PlayerDto): Player? {
        if (id < 0 || id >= playerList.size) {
            return null
        }
        val newPlayer = Player(
            id,
            playerDto.name,
            playerDto.country,
            playerDto.position,
            playerDto.height,
            playerDto.leadingFoot,
            playerDto.goals,
            playerDto.saves
        )
        playerList[id] = newPlayer
        return newPlayer
    }

    fun getAll(): List<Player> {
        return playerList
    }
}