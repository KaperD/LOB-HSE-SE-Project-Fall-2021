package ru.hse.sport.football.player.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class PlayerNotFoundException(id: Int) : RuntimeException("Player with id $id not found")
