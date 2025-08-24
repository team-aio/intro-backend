package aio.intro.application.log.required

import aio.intro.domain.log.UserEventLog
import org.springframework.data.repository.Repository

interface UserEventLogRepository : Repository<UserEventLog, Long> {
    fun save(userEventLog: UserEventLog): UserEventLog
}