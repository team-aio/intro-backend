package aio.intro.domain.log

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserEventLogTest : StringSpec({
    "FIRST_VISIT은 생성시에 설정할 수 없다." {
        shouldThrow<IllegalArgumentException> {
            UserEventLog.create("test", "test", EventType.FIRST_VISIT, null, DeviceType.MOBILE, MediaType.EVERY_TIME)
        }
    }
    "REVISIT은 생성시에 설정할 수 없다." {
        shouldThrow<IllegalArgumentException> {
            UserEventLog.create("test", "test", EventType.REVISIT, null, DeviceType.MOBILE, MediaType.EVERY_TIME)
        }
    }
    "EXIT은 changeToFirstVisit을 호출할 수 없다." {
        val userEventLog =
            UserEventLog.create("test", "test", EventType.EXIT, null, DeviceType.MOBILE, MediaType.EVERY_TIME)

        shouldThrow<IllegalStateException> {
            userEventLog.changeToFirstVisit()
        }
    }
    "BUTTON_CLICK은 changeToRevisit을 호출할 수 없다." {
        val userEventLog =
            UserEventLog.create("test", "test", EventType.BUTTON_CLICK, null, DeviceType.MOBILE, MediaType.EVERY_TIME)

        shouldThrow<IllegalStateException> {
            userEventLog.changeToRevisit()
        }
    }
    "VISIT은 changeToFirstVisit을 호출할 수 있다." {
        val userEventLog =
            UserEventLog.create("test", "test", EventType.VISIT, null, DeviceType.MOBILE, MediaType.EVERY_TIME)

        userEventLog.changeToFirstVisit()

        userEventLog.eventType shouldBe EventType.FIRST_VISIT
    }
    "VISIT은 changeToRevisit을 호출할 수 있다." {
        val userEventLog =
            UserEventLog.create("test", "test", EventType.VISIT, null, DeviceType.MOBILE, MediaType.EVERY_TIME)

        userEventLog.changeToRevisit()

        userEventLog.eventType shouldBe EventType.REVISIT
    }
})
