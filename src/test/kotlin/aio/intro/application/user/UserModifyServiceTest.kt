package aio.intro.application.user

import aio.intro.application.user.required.UserRepository
import aio.intro.boot.IntroApplication
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@Transactional
@SpringBootTest(classes = [IntroApplication::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserModifyServiceTest(
    private val userModifyService: UserModifyService,
    private val entityManager: EntityManager,
    private val userRepository: UserRepository
) : StringSpec() {
    override fun extensions(): List<Extension> = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))

    init {
        "register()를 호출하면 User가 생성되어야 한다" {
            userModifyService.register("test", "service1")

            entityManager.flush()
            entityManager.clear()

            val user = userRepository.findByIdentifier("test")

            user!!.serviceName shouldBe "service1"
        }
    }
}