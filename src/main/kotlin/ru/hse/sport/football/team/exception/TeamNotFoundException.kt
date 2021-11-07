package ru.hse.sport.football.team.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class TeamNotFoundException(id: Int) : RuntimeException("A team with id $id not found")