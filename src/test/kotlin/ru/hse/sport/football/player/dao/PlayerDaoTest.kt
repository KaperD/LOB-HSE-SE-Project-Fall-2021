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

    @Test
    fun `test save user`() {
        val playerDto = PlayerDto(
            "Danil",
            "Russia",
            "Goalkeeper",
            190,
            "Right",
            1,
            123
        )
        val player: Player = playerDao.save(playerDto)
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
}