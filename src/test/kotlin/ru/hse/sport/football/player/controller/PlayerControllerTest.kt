package ru.hse.sport.football.player.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
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

    @Test
    fun `test getting player correct response`() {
        val json = getResource("goalkeeper.json")
        val goalkeeper = postPlayer(json, Player::class.java).body!!

        val GETResponse = getPlayer(goalkeeper.id, Player::class.java)

        assertEquals(HttpStatus.OK, GETResponse.statusCode)
        assertEquals(goalkeeper, GETResponse.body!!)
    }

    @Test
    fun `test getting player with wrong id`() {
        val response = getPlayer(-1, String::class.java)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `test getting all players`() {
        val responseBefore = getAllPlayers(String::class.java)
        assertEquals(HttpStatus.OK, responseBefore.statusCode)
        val allPlayersBefore = Json.parseToJsonElement(responseBefore.body!!)

        val forwardJson = getResource("forward.json")
        val goalkeeperJson = getResource("goalkeeper.json")

        val goalkeeper = postPlayer(goalkeeperJson, Player::class.java).body!!
        val forward = postPlayer(forwardJson, Player::class.java).body!!

        val responseAfter = getAllPlayers(String::class.java)
        assertEquals(HttpStatus.OK, responseAfter.statusCode)
        val allPlayersAfter = Json.parseToJsonElement(responseAfter.body!!)

        assertEquals(allPlayersBefore.jsonArray.size, allPlayersAfter.jsonArray.size - 2)
        val numberOfPlayers = allPlayersAfter.jsonArray.size

        val mapper = jacksonObjectMapper()

        assertEquals(goalkeeper, mapper.readValue(allPlayersAfter.jsonArray[numberOfPlayers - 2].toString(), Player::class.java))
        assertEquals(forward, mapper.readValue(allPlayersAfter.jsonArray[numberOfPlayers - 1].toString(), Player::class.java))
    }

    fun checkModelFitsDto(player: Player, playerDto: PlayerDto) {
        assertEquals(playerDto.name, player.name)
        assertEquals(playerDto.country, player.country)
        assertEquals(playerDto.position, player.position)
        assertEquals(playerDto.height, player.height)
        assertEquals(playerDto.leadingFoot, player.leadingFoot)
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

    fun <T> getPlayer(playerId: Int, clazz: Class<T>): ResponseEntity<T> {
        return template.getForEntity(
            "/football/player/$playerId",
            clazz
        )
    }

    fun <T> getAllPlayers(clazz: Class<T>): ResponseEntity<T> {
        return template.getForEntity(
            "/football/player/all",
            clazz
        )
    }
}
