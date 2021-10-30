package ru.hse.sport.football.player.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*

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
            postPlayer(forward).statusCode
        )
    }

    fun getResource(path: String): String {
        return this::class.java.getResource(path)!!.readText()
    }

    fun postPlayer(playerJson: String): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return template.postForEntity(
            "/football/player",
            HttpEntity(playerJson, headers),
            String::class.java
        )
    }
}
