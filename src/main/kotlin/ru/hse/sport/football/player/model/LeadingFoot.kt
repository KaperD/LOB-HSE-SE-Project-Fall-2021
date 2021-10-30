package ru.hse.sport.football.player.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class LeadingFoot {
    @JsonProperty("left")
    LEFT,
    @JsonProperty("right")
    RIGHT,
    @JsonProperty("both")
    BOTH
}