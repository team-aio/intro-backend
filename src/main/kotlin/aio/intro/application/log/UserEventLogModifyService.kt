package aio.intro.application.log

import aio.intro.application.log.provided.UserActionLogging
import aio.intro.application.log.required.UserEventLogRepository
import aio.intro.domain.log.UserActionLoggingRequest
import aio.intro.domain.log.UserEventLog
import org.springframework.stereotype.Service

@Service
class UserEventLogModifyService(
    private val userEventLogRepository: UserEventLogRepository,
) : UserActionLogging {
    override fun logUserAction(logRequest: UserActionLoggingRequest) {
        val userEventLog = UserEventLog.createLogRequest(logRequest)
        
        userEventLogRepository.save(userEventLog)
    }
}