package ru.hse.sport.football.team.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import ru.hse.sport.football.SpringTest
import ru.hse.sport.football.player.checkModelFitsDto
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto

class TeamControllerTest : SpringTest {

    @Autowired
    lateinit var template: TestRestTemplate

    val emptyZenitDto = TeamDto(
        "zenit",
        "Russia",
        "Premier",
        "Газпром Арена",
        "Газпром",
        "Сергей Богданович Семак"
    )

    val emptyPSGDto = TeamDto(
        "PSG",
        "France",
        "Premier",
        "Parc de prens",
        "Sponsor",
        "Mauricio Roberto Pochettino Trossero"
    )

    @Test
    fun `test adding correct team`() {
        val zenitDto = emptyZenitDto

        val mapper = jacksonObjectMapper()

        val zenit = postTeam(mapper.writeValueAsString(zenitDto), Team::class.java)

        Assertions.assertEquals(HttpStatus.OK, zenit.statusCode)
        checkModelFitsDto(zenit.body!!, zenitDto)

        val psgDto = emptyPSGDto

        val psg = postTeam(mapper.writeValueAsString(psgDto), Team::class.java)

        Assertions.assertEquals(HttpStatus.OK, psg.statusCode)
        checkModelFitsDto(psg.body!!, psgDto)

        Assertions.assertNotEquals(zenit.body!!.id, psg.body!!.id)
    }

    @Test
    fun `test adding team with empty name`() {
        val mapper = jacksonObjectMapper()

        val zenitDto = emptyZenitDto.copy(name = "")
        val response = postTeam(mapper.writeValueAsString(zenitDto), String::class.java)
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    @Test
    fun `test getting correct team`() {
        val mapper = jacksonObjectMapper()

        val psgDto = emptyPSGDto

        val psg = postTeam(mapper.writeValueAsString(psgDto), Team::class.java).body!!

        val psgGetResponse = getTeam(psg.id, Team::class.java)

        Assertions.assertEquals(HttpStatus.OK, psgGetResponse.statusCode)
        Assertions.assertEquals(psg, psgGetResponse.body!!)
    }

    @Test
    fun `test getting team with wrong id`() {
        val mapper = jacksonObjectMapper()

        Assertions.assertEquals(
            HttpStatus.OK,
            postTeam(mapper.writeValueAsString(emptyZenitDto), Team::class.java).statusCode
        )

        val response = getTeam(-1, String::class.java)

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `test updating team`() {
        val mapper = jacksonObjectMapper()

        val zenitDto = emptyZenitDto

        val zenit = postTeam(mapper.writeValueAsString(zenitDto), Team::class.java).body!!

        val updatedZenitDto = emptyZenitDto.copy(coachName = "Alexey Luchinin")

        val updatedZenitResponse = putTeam(zenit.id, mapper.writeValueAsString(updatedZenitDto), Team::class.java)
        val updatedZenit = getTeam(zenit.id, Team::class.java).body!!

        Assertions.assertEquals(HttpStatus.OK, updatedZenitResponse.statusCode)
        checkModelFitsDto(updatedZenit, updatedZenitDto)
        Assertions.assertNotEquals(zenit, updatedZenit)
    }

    @Test
    fun `test updating non-existing team`() {
        val mapper = jacksonObjectMapper()

        Assertions.assertEquals(
            HttpStatus.OK,
            postTeam(mapper.writeValueAsString(emptyPSGDto), Team::class.java).statusCode
        )

        val wrongId = -90

        val updatedPSGDto = emptyPSGDto

        val updatedPSGResponse = putTeam(wrongId, mapper.writeValueAsString(updatedPSGDto), String::class.java)
        val getResponse = getTeam(wrongId, String::class.java)

        Assertions.assertEquals(HttpStatus.NOT_FOUND, updatedPSGResponse.statusCode)
        Assertions.assertEquals(HttpStatus.NOT_FOUND, getResponse.statusCode)
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

    fun <T> postTeam(teamJson: String, clazz: Class<T>): ResponseEntity<T> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return template.postForEntity(
            "/football/team",
            HttpEntity(teamJson, headers),
            clazz
        )
    }

    fun <T> getTeam(teamId: Int, clazz: Class<T>): ResponseEntity<T> {
        return template.getForEntity(
            "/football/team/$teamId",
            clazz
        )
    }

    fun <T> putTeam(id: Int, teamJson: String, clazz: Class<T>): ResponseEntity<T> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        return template.exchange(
            "/football/team/$id",
            HttpMethod.PUT,
            HttpEntity(teamJson, headers),
            clazz
        )
    }
}
