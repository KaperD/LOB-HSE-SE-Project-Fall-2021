package ru.hse.sport.football.player.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import ru.hse.sport.football.player.model.Player
import ru.hse.sport.football.player.model.PlayerDto

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerControllerTest {
    val playerFields = arrayOf("name", "country", "position", "height", "leadingFoot", "goals", "saves")

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    fun `test adding correct player`() {
        val forward = getResource("forward.json")
        assertEquals(
            HttpStatus.OK,
            postPlayer(forward, String::class.java).statusCode
        )
    }

    @Test
    fun `test correct response after adding correct player`() {
        val forwardJson = getResource("forward.json")
        val goalkeeperJson = getResource("goalkeeper.json")

        val forward = postPlayer(forwardJson, Player::class.java).body!!
        val goalkeeper = postPlayer(goalkeeperJson, Player::class.java).body!!

        assertNotEquals(forward.id, goalkeeper.id)

        val mapper = jacksonObjectMapper()
        val forwardDto = mapper.readValue(forwardJson, PlayerDto::class.java)
        val goalkeeperDto = mapper.readValue(goalkeeperJson, PlayerDto::class.java)

        checkModelFitsDto(forward, forwardDto)
        checkModelFitsDto(goalkeeper, goalkeeperDto)
    }

    @Test
    fun `test adding incorrect player`() {
        val negativeHeight = getResource("negative_height.json")
        val negativeGoals = getResource("negative_goals.json")
        val noName = getResource("no_name.json")
        assertEquals(
            HttpStatus.BAD_REQUEST,
            postPlayer(negativeHeight, String::class.java).statusCode
        )
        assertEquals(
            HttpStatus.BAD_REQUEST,
            postPlayer(negativeGoals, String::class.java).statusCode
        )
        assertEquals(
            HttpStatus.BAD_REQUEST,
            postPlayer(noName, String::class.java).statusCode
        )
    }

    fun checkModelFitsDto(player: Player, playerDto: PlayerDto) {
        assertEquals(playerDto.name, player.name)
        assertEquals(playerDto.country, player.country)
        assertEquals(playerDto.position, player.position)
        assertEquals(playerDto.height, player.height)
        assertEquals(playerDto.leadingFoot.uppercase(), player.leadingFoot)
        assertEquals(playerDto.goals, player.goals)
        assertEquals(playerDto.saves, player.saves)
    }

    fun getResource(path: String): String {
        return this::class.java.getResource(path)!!.readText()
    }

    fun <T> postPlayer(playerJson: String, clazz: Class<T>): ResponseEntity<T> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return template.postForEntity(
            "/football/player",
            HttpEntity(playerJson, headers),
            clazz
        )
    }
}
