package aio.intro.domain.user

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class UserTest : StringSpec({
    "User를 생성하면 처음에는 ACTIVE 상태여야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())
        user.userStatus shouldBe UserStatus.ACTIVE
    }
})
