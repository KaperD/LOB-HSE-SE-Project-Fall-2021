package ru.hse.sport.football.team.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TeamDto(
    @field:NotBlank(message = "Team name cannot be empty")
    val name: String,
    val country: String,
    val league: String,
    val homeStadium: String,
    val generalSponsor: String,
    val coachName: String,
    @field:NotNull(message = "It is better to specify an empty set than null")
    val playersIds: Set<Int>
)
