package ru.hse.sport.football.team.model

import javax.validation.constraints.NotBlank

data class TeamDto(
    @field:NotBlank(message = "Team name cannot be empty")
    val name: String,
    val country: String,
    val league: String,
    val homeStadium: String,
    val generalSponsor: String,
    val coachName: String
)
