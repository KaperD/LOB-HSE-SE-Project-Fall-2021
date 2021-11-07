package ru.hse.sport.football.team.model

data class Team(
    val id: Int,
    val name: String,
    val country: String,
    val league: String,
    val homeStadium: String,
    val generalSponsor: String,
    val coachName: String,
    val playersIds: Set<Int>
)
