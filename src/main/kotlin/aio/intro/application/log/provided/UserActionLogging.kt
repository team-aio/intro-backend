package aio.intro.application.log.provided

import aio.intro.domain.log.UserEventLog

/**
 * 유저의 행동을 기록하는 기능을 제공
 */
interface UserActionLogging {
    fun logUserAction(userEventLog: UserEventLog)
}