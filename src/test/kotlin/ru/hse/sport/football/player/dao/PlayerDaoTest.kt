package ru.hse.sport.football.player.dao

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

@SpringBootTest
class PlayerDaoTest {

    @Autowired
    lateinit var playerDao: PlayerDao

    val goalkeeperDto = PlayerDto(
        "Danil",
        "Russia",
        "Goalkeeper",
        190,
        "Right",
        1,
        123
    )

    @Test
    fun `test save player`() {
        val playerDto = PlayerDto(
            "Danil",
            "Russia",
            "Goalkeeper",
            190,
            "Right",
            1,
            123
        )
        val player: Player = playerDao.save(goalkeeperDto)
        val expectedPlayer = Player(
            player.id,
            playerDto.name,
            playerDto.country,
            playerDto.position,
            playerDto.height,
            playerDto.leadingFoot,
            playerDto.goals,
            playerDto.saves
        )
        assertEquals(expectedPlayer, player)
    }

    @Test
    fun `test get player`() {
        val player: Player = playerDao.save(goalkeeperDto)
        val gottenPlayer: Player = playerDao.getById(player.id).get()
        assertEquals(player, gottenPlayer)
    }

    @Test
    fun `test get all players`() {
        val numberOfPlayers = playerDao.getNumberOfPlayers()
        val player: Player = playerDao.save(goalkeeperDto)
        val gottenPlayers: List<Player> = playerDao.getAll()

        assertEquals(numberOfPlayers + 1, gottenPlayers.size)
        assertEquals(player, gottenPlayers.last())
    }
}