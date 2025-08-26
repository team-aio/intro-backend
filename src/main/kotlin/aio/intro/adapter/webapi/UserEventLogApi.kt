package aio.intro.adapter.webapi

import aio.intro.adapter.webapi.dto.request.UserActionLoggingRequest
import aio.intro.application.log.provided.UserActionLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/logs")
@Tag(name = "UserEventLog", description = "유저 이벤트 로그 관련 API")
class UserEventLogApi(
    private val userActionLogging: UserActionLogging
) {
    @Operation(summary = "유저 행동 로그 기록", description = "유저 행동 로그를 기록하는 API 입니다.")
    @PostMapping("/users")
    fun logUserEvent(@RequestBody request: UserActionLoggingRequest) {
        userActionLogging.logUserAction(request.toEntity())
    }
}