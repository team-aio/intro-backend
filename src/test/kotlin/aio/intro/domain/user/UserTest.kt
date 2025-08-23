package aio.intro.domain.user

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class UserTest : StringSpec({
    "User를 생성하면 처음에는 ACTIVE 상태여야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())
        user.userStatus shouldBe UserStatus.ACTIVE
    }
    "User를 생성한 후, activate를 호출하면 IllegalStateException이 발생해야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())

        shouldThrow<IllegalStateException> {
            user.activate()
        }
    }
    "User를 생성한 후 deactivate를 호출하면, INACTIVE 상태가 되어야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())

        user.deactivate()

        user.userStatus shouldBe UserStatus.INACTIVE
    }

    "User를 생성한 후, deactivate를 호출한 후 activate를 호출하면, ACTIVE 상태가 되어야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())

        user.deactivate()
        user.activate()

        user.userStatus shouldBe UserStatus.ACTIVE
    }

    "User를 생성한 후, activate를 두번 호출하면, IllegalStateException이 발생해야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())

        user.deactivate()
        user.activate()

        shouldThrow<IllegalStateException> {
            user.activate()
        }
    }

    "User를 생성한 후, deactivate를 두번 호출하면, IllegalStateException이 발생해야 한다." {
        val user = User.create("test", "test", LocalDateTime.now())

        user.deactivate()

        shouldThrow<IllegalStateException> {
            user.deactivate()
        }
    }
})
