package aio.intro.application.user.provided

/**
 * 유저 등록에 관한 기능을 제공
 */
interface UserAction {
    fun enterIntro(identifier: String, serviceName: String)
    fun exitIntro(identifier: String)
}