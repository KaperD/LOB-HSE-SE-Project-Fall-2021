package ru.hse.sport.football.player.model

data class PlayerDto(
    val name: String,
    val country: String,
    val position: String,
    val height: Int,
    val leadingFoot: String,
    val goals: Int,
    val saves: Int
)
