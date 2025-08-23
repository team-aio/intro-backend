package aio.intro.application.user

import aio.intro.application.user.required.UserRepository
import aio.intro.boot.IntroApplication
import aio.intro.domain.user.UserStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
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
        "기존에 존재하지 않는 유저를 등록하면, 유저가 생성되어야 한다." {
            userModifyService.enterIntro("test1", "service1")

            val user = userRepository.findByIdentifier("test1")

            user shouldNotBe null
        }
        "기존에 존재하는 유저를 등록하면, 유저가 활성화되어야 한다." {
            userModifyService.enterIntro("test1", "service1")
            val user = userRepository.findByIdentifier("test1")

            user?.userStatus shouldBe UserStatus.ACTIVE
        }
        "identifier가 같은 유저가 등록하면 예외가 발생해야한다." {
            userModifyService.enterIntro("test1", "service1")

            shouldThrow<IllegalStateException> {
                userModifyService.enterIntro("test1", "service2")
            }
        }
    }
}