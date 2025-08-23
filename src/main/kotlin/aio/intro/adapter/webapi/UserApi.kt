package aio.intro.adapter.webapi

import aio.intro.application.user.provided.UserAction
import aio.intro.domain.user.UserEnterIntroRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
@Tag(name = "Member", description = "회원 관련 API")
class UserApi(
    private val userAction: UserAction
) {
    @PostMapping("/enter")
    @Operation(summary = "유저 입장", description = "유저가 입장하면 호출하는 API")
    fun userEnter(@RequestBody request: UserEnterIntroRequest) {
        userAction.enterIntro(request)
    }
}