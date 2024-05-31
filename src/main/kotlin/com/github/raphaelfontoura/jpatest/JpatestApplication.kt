package com.github.raphaelfontoura.jpatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpatestApplication

fun main(args: Array<String>) {
	runApplication<JpatestApplication>(*args)
}
