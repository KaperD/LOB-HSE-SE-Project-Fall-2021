package ru.hse.sport.football.team.controller

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.hse.sport.football.team.exception.TeamNotFoundException
import ru.hse.sport.football.team.model.Team
import ru.hse.sport.football.team.model.TeamDto
import ru.hse.sport.football.team.service.TeamService

@RestController
@RequestMapping("/football/team")
class TeamController(
    private val teamService: TeamService
) {
    @PostMapping
    fun createTeam(@Validated @RequestBody teamDto: TeamDto): Team {
        return teamService.createTeam(teamDto)
    }

    @GetMapping("/{id}")
    fun getTeam(@PathVariable id: Int): Team {
        return teamService.getTeam(id) ?: throw TeamNotFoundException(id)
    }

    @PutMapping("/{id}")
    fun updateTeam(
        @PathVariable id: Int,
        @Validated @RequestBody teamDto: TeamDto
    ): Team {
        return teamService.updateTeam(id, teamDto) ?: throw TeamNotFoundException(id)
    }
}
