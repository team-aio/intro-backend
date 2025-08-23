package aio.intro.domain.user

data class UserEnterIntroRequest(
    val identifier: String,
    val serviceName: String,
)
