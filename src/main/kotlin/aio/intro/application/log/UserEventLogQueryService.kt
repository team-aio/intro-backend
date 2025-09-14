package aio.intro.application.log

import aio.intro.application.log.provided.UserEventLogFinder
import aio.intro.application.log.required.UserEventLogRepository
import org.springframework.stereotype.Service

@Service
class UserEventLogQueryService(
    private val userEventLogRepository: UserEventLogRepository
) : UserEventLogFinder {
    override fun existsBy(identifier: String, serviceName: String): Boolean {
        return userEventLogRepository.existsByIdentifierAndServiceName(identifier, serviceName)
    }
}