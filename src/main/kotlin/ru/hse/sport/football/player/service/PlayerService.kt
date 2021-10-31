package ru.hse.sport.football.player.service

import org.springframework.stereotype.Service
import ru.hse.sport.football.player.dao.PlayerDao
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

@Service
class PlayerService(
    private val playerDao: PlayerDao
) {

    fun createPlayer(playerDto: PlayerDto): Player {
        return playerDao.save(playerDto)
    }

    fun getPlayer(id: Int): Player? {
        return playerDao.getById(id)
    }

    fun getAllPlayers(): List<Player> = playerDao.getAll()

    fun updatePlayer(id: Int, updatedPlayerDto: PlayerDto): Player? {
        return playerDao.update(id, updatedPlayerDto)
    }
}
