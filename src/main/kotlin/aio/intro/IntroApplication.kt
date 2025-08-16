package aio.intro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IntroApplication

fun main(args: Array<String>) {
    runApplication<IntroApplication>(*args)
}
