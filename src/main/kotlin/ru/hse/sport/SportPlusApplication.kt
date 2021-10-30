package ru.hse.sport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SportPlusApplication

fun main(args: Array<String>) {
	runApplication<SportPlusApplication>(*args)
}
