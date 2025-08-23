package aio.intro.application.user.provided

import aio.intro.domain.user.UserEnterIntroRequest

/**
 * 유저 등록에 관한 기능을 제공
 */
interface UserAction {
    fun enterIntro(request: UserEnterIntroRequest)
}