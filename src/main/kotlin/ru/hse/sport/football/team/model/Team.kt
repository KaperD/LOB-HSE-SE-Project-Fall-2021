package ru.hse.sport.football.team.model

data class Team(
    val id: Int,
    val name: String,
    val country: String,
    val league: String,
    val homeStadium: String,
    val generalSponsor: String,
    val coachName: String,
    val playersIds: Array<Int>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        if (id != other.id) return false
        if (name != other.name) return false
        if (country != other.country) return false
        if (league != other.league) return false
        if (homeStadium != other.homeStadium) return false
        if (generalSponsor != other.generalSponsor) return false
        if (coachName != other.coachName) return false
        if (!playersIds.contentEquals(other.playersIds)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + league.hashCode()
        result = 31 * result + homeStadium.hashCode()
        result = 31 * result + generalSponsor.hashCode()
        result = 31 * result + coachName.hashCode()
        result = 31 * result + playersIds.contentHashCode()
        return result
    }
}
