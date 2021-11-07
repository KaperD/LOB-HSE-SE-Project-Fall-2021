package ru.hse.sport.football.player.model

data class Player(
    val id: Int,
    val name: String,
    val country: String,
    val position: String,
    val height: Int,
    val leadingFoot: String,
    val goals: Int,
    val saves: Int,
    val teamId: Int?
)
