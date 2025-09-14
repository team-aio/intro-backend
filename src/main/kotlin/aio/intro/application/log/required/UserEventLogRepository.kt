package aio.intro.application.log.required

import aio.intro.domain.log.UserEventLog
import org.springframework.data.repository.Repository
import java.time.LocalDateTime

interface UserEventLogRepository : Repository<UserEventLog, Long> {
    fun save(userEventLog: UserEventLog): UserEventLog

    fun findAllByCreatedAtGreaterThanAndCreatedAtLessThanEqual(
        startExclusive: LocalDateTime,
        endInclusive: LocalDateTime
    ): List<UserEventLog>

    fun existsByIdentifierAndServiceName(identifier: String, serviceName: String): Boolean

    fun findByIdentifier(identifier: String): UserEventLog?
}