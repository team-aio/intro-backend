package aio.intro.adapter.batch

import aio.intro.application.log.required.UserActionStatisticsDatabase
import aio.intro.application.log.required.UserEventLogRepository
import aio.intro.domain.log.UserEventLog
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NotionScheduler(
    private val userEventLogRepository: UserEventLogRepository,
    private val userActionStatisticsDatabase: UserActionStatisticsDatabase
) {
    @Scheduled(cron = "0 0 4 * * *") // 매일 4시에 실행
    fun syncDBToNotion() {
        val latestLogsByServiceName = findLatestLogsByServiceName()

        latestLogsByServiceName.forEach { serviceName, logs ->
            addToNotion(logs, serviceName)
        }
    }

    private fun findLatestLogsByServiceName(): Map<String, List<UserEventLog>> =
        userEventLogRepository
            .findAllByCreatedAtGreaterThanAndCreatedAtLessThanEqual(
                startExclusive = LocalDateTime.now().minusDays(1),
                endInclusive = LocalDateTime.now()
            ).groupBy { it.serviceName }

    private fun addToNotion(logs: List<UserEventLog>, serviceName: String) {
        val visitorCount = logs.filter { it.isFirstVisit() }.size.toLong()
        val emailButtonClickCount = logs.filter { it.isEmailButtonClick() }.size.toLong()

        userActionStatisticsDatabase.addRow(serviceName, serviceName, visitorCount, emailButtonClickCount)
    }
}