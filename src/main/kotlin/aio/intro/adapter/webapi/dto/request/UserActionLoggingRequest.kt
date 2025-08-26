package aio.intro.adapter.webapi.dto.request

import aio.intro.domain.log.DeviceType
import aio.intro.domain.log.EventType
import aio.intro.domain.log.MediaType
import aio.intro.domain.log.UserEventLog

data class UserActionLoggingRequest(
    val identifier: String,
    val serviceName: String,
    val eventType: String,
    val metaData: String? = null,
    val deviceType: String,
    val mediaType: String,
) {
    fun toEntity() = UserEventLog.create(
        identifier,
        serviceName,
        EventType.from(eventType),
        metaData,
        DeviceType.from(deviceType),
        MediaType.from(mediaType)
    )
}