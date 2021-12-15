package ru.hse.sport.football.player.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class PlayerDto(
    @field:NotBlank(message = "Player name cannot be empty")
    val name: String,
    @field:NotBlank(message = "None of countries has an empty name")
    val country: String,
    @field:NotBlank(message = "We are sure that your position name consist of one letter at least")
    val position: String,
    @field:Min(value = 1, message = "Are you really so small?")
    val height: Int,
    @field:NotBlank(message = "Tell us about your leading foot")
    val leadingFoot: String,
    @field:Min(value = 0, message = "You cannot be so bad in football")
    val goals: Int = 0,
    @field:Min(value = 0, message = "We are sure that your saves at least non-negative")
    val saves: Int = 0,
    val teamId: Int?
)
