package ru.hse.sport.football.player.dao

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.hse.sport.football.player.checkModelFitsDto
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

    val forwardDto = PlayerDto(
        "Alex",
        "Canada",
        "Center forward",
        191,
        "Left",
        11,
        0
    )

    @Test
    fun `test save player`() {
        val player: Player = playerDao.save(goalkeeperDto)
        val expectedPlayer = Player(
            player.id,
            goalkeeperDto.name,
            goalkeeperDto.country,
            goalkeeperDto.position,
            goalkeeperDto.height,
            goalkeeperDto.leadingFoot,
            goalkeeperDto.goals,
            goalkeeperDto.saves
        )
        assertEquals(expectedPlayer, player)
    }

    @Test
    fun `test save players unique ids`() {
        val player1 = playerDao.save(goalkeeperDto)
        val player2 = playerDao.save(forwardDto)
        assertNotEquals(player1.id, player2.id)
    }

    @Test
    fun `test get player`() {
        val player: Player = playerDao.save(goalkeeperDto)
        val gottenPlayer: Player? = playerDao.getById(player.id)
        assertNotNull(gottenPlayer)
        assertEquals(player, gottenPlayer)
    }

    @Test
    fun `test get multiple players`() {
        for (i in 0..10) {
            val player: Player = playerDao.save(goalkeeperDto)
            val gottenPlayer: Player? = playerDao.getById(player.id)
            assertNotNull(gottenPlayer)
            assertEquals(player, gottenPlayer)
        }
    }

    @Test
    fun `test get all players`() {
        val numberOfPlayers = playerDao.getNumberOfPlayers()
        val player: Player = playerDao.save(goalkeeperDto)
        val gottenPlayers: List<Player> = playerDao.getAll()

        assertEquals(numberOfPlayers + 1, gottenPlayers.size)
        assertEquals(player, gottenPlayers.last())
    }

    @Test
    fun `test update player`() {
        val forward = playerDao.save(forwardDto)
        val newPosition = "programmer"

        val updatedForwardDto = PlayerDto(
            forwardDto.name,
            forwardDto.country,
            newPosition,
            forwardDto.height,
            forwardDto.leadingFoot,
            forwardDto.goals,
            forwardDto.saves
        )

        val updatedForward = playerDao.update(forward.id, updatedForwardDto)!!
        checkModelFitsDto(updatedForward, updatedForwardDto)
        checkModelFitsDto(playerDao.getById(forward.id)!!, updatedForwardDto)
    }

    @Test
    fun `test update player with wrong id`() {
        assertNull(playerDao.update(-1, goalkeeperDto))
        assertNull(playerDao.update(playerDao.getAll().size, goalkeeperDto))
    }
}