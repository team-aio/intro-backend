package aio.intro.application.log

import aio.intro.application.log.required.UserEventLogRepository
import aio.intro.boot.IntroApplication
import aio.intro.domain.log.DeviceType
import aio.intro.domain.log.EventType
import aio.intro.domain.log.MediaType
import aio.intro.domain.log.UserEventLog
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [IntroApplication::class])
@Transactional
class UserEventLogModifyServiceTest(
    @Autowired private val userEventLogModifyService: UserEventLogModifyService,
    @Autowired private val userEventLogRepository: UserEventLogRepository
) : StringSpec({
    "VISIT 이벤트를 저장하면, FIRST_VISIT 이벤트로 저장되어야 한다." {
        val userEventLog = UserEventLog.create(
            "test",
            "test",
            EventType.VISIT,
            null,
            DeviceType.MOBILE,
            MediaType.EVERY_TIME
        )
        userEventLogModifyService.logUserAction(userEventLog)

        val savedUserEventLog = userEventLogRepository.findByIdentifier(userEventLog.identifier).first()

        savedUserEventLog.eventType shouldBe EventType.FIRST_VISIT
    }
    "VISIT 이벤트를 저장하고, 같은 유저의 VISIT 이벤트를 저장하면, REVISIT 이벤트로 저장되어야 한다." {
        val userEventLog = UserEventLog.create(
            "test",
            "test",
            EventType.VISIT,
            null,
            DeviceType.MOBILE,
            MediaType.EVERY_TIME
        )
        userEventLogModifyService.logUserAction(userEventLog)

        val userEventLog2 = UserEventLog.create(
            "test",
            "test",
            EventType.VISIT,
            null,
            DeviceType.MOBILE,
            MediaType.EVERY_TIME
        )
        userEventLogModifyService.logUserAction(userEventLog2)

        val savedUserEventLog = userEventLogRepository.findByIdentifier(userEventLog.identifier)

        savedUserEventLog.map { it.eventType } shouldContain EventType.REVISIT
    }

}) {
    override fun extensions(): List<Extension> = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))
}
