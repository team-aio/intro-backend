package aio.intro.adapter.webapi.dto.request

import aio.intro.domain.log.DeviceType
import aio.intro.domain.log.EventType
import aio.intro.domain.log.MediaType
import aio.intro.domain.log.UserEventLog
import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "유저 행동 로그 기록 Request", description = "유저 행동 로그 기록 요청")
data class UserActionLoggingRequest(
    @Schema(title = "식별자(세션값)", description = "유저를 식별할 수 있는 값", example = "test")
    val identifier: String,
    @Schema(title = "서비스명", description = "어떤 서비스에서 발생한 이벤트인지", example = "Toasting")
    val serviceName: String,
    @Schema(
        title = "이벤트 타입",
        description = "유저 행동 이벤트 인지,",
        allowableValues = ["VISIT", "EXIT", "SCROLL", "BUTTON_CLICK"],
        example = "VISIT"
    )
    val eventType: String,

    @Schema(title = "메타데이터", description = "이벤트에 대한 추가 정보", example = "email")
    val metaData: String? = null,
    
    @Schema(
        title = "디바이스 타입",
        description = "어떤 디바이스에서 발생한 이벤트인지",
        allowableValues = ["MOBILE", "PC", "ETC"],
        example = "MOBILE"
    )
    val deviceType: String,

    @Schema(
        title = "미디어 타입",
        description = "어떤 미디어를 통해 발생한 이벤트인지",
        allowableValues = ["EVERY_TIME", "KAKAO_TALK", "INSTAGRAM", "ETC"],
        example = "EVERY_TIME"
    )
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