package aio.intro.application.user.provided

/**
 * 유저 등록에 관한 기능을 제공
 */
interface UserRegister {
    fun register(identifier: String, serviceName: String)
    fun activate(identifier: String)
}