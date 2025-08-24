package aio.intro.domain.log

data class UserActionLoggingRequest(
    val identifier: String,
    val serviceName: String,
    val eventType: String,
    val metaData: String? = null,
    val deviceType: String,
    val mediaType: String,
)
