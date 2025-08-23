package aio.intro.application.user

import aio.intro.application.user.required.UserRepository
import aio.intro.boot.IntroApplication
import aio.intro.domain.user.User
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.time.LocalDateTime

@SpringBootTest(classes = [IntroApplication::class])
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserQueryServiceTest(
    private val userRepository: UserRepository,
    private val userQueryService: UserQueryService
) : StringSpec() {
    override fun extensions(): List<Extension> = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))

    init {
        "기존에 존재하는 유저를 조회하면, 해당 유저를 반환해야 한다." {
            val user = User.create("test1", "service1", LocalDateTime.now())
            userRepository.save(user)

            val foundUser = userQueryService.findBy("test1")

            foundUser shouldBe user
        }

        "기존에 존재하지 않는 유저를 조회하면 IllegalArgumentException이 발생해야 한다." {
            val user = User.create("test1", "service1", LocalDateTime.now())
            userRepository.save(user)

            shouldThrow<IllegalArgumentException> {
                userQueryService.findBy("test2")
            }
        }
        "기존에 존재하는 유저를 조회하면, true를 반환해야 한다." {
            val user = User.create("test1", "service1", LocalDateTime.now())
            userRepository.save(user)

            userQueryService.existsBy("test1") shouldBe true
        }
        "기존에 존재하지 않는 유저를 조회하면, false를 반환해야 한다." {
            val user = User.create("test1", "service1", LocalDateTime.now())
            userRepository.save(user)

            userQueryService.existsBy("test2") shouldBe false
        }
    }
}
