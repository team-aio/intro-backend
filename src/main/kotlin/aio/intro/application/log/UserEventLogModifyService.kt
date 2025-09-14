package aio.intro.application.log

import aio.intro.application.log.provided.UserActionLogging
import aio.intro.application.log.provided.UserEventLogFinder
import aio.intro.application.log.required.UserEventLogRepository
import aio.intro.domain.log.UserEventLog
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserEventLogModifyService(
    private val userEventLogRepository: UserEventLogRepository,
    private val userEventLogFinder: UserEventLogFinder
) : UserActionLogging {
    override fun logUserAction(userEventLog: UserEventLog) {
        if (userEventLog.isVisit()) {
            changeVisit(userEventLog)
        }
        
        userEventLogRepository.save(userEventLog)
    }

    private fun changeVisit(userEventLog: UserEventLog) {
        if (userEventLogFinder.existsBy(userEventLog.identifier, userEventLog.serviceName)) {
            userEventLog.changeToRevisit()
            return
        }

        userEventLog.changeToFirstVisit()
    }
}