package aio.intro.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["aio.intro"])
@EnableJpaAuditing
@EntityScan("aio.intro.domain")
@EnableJpaRepositories("aio.intro")
class IntroApplication

fun main(args: Array<String>) {
    runApplication<IntroApplication>(*args)
}
