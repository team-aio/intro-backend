package aio.intro.application.log

import aio.intro.application.log.provided.UserActionLogging
import aio.intro.application.log.required.UserEventLogRepository
import aio.intro.domain.log.UserEventLog
import org.springframework.stereotype.Service

@Service
class UserEventLogModifyService(
    private val userEventLogRepository: UserEventLogRepository,
) : UserActionLogging {
    override fun logUserAction(userEventLog: UserEventLog) {
        userEventLogRepository.save(userEventLog)
    }
}