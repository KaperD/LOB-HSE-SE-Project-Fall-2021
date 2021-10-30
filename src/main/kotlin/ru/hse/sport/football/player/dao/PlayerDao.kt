package ru.hse.sport.football.player.dao

import org.springframework.stereotype.Component
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

@Component
class PlayerDao {
    private val playerList : ArrayList<Player> = arrayListOf()

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
}