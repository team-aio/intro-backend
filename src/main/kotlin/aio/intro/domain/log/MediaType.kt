package aio.intro.domain.log

enum class MediaType {
    EVERY_TIME,
    KAKAO_TALK,
    INSTAGRAM,
    ETC;

    companion object {
        fun from(name: String) = when (name.lowercase()) {
            "every_time" -> EVERY_TIME
            "kakao_talk" -> KAKAO_TALK
            "instagram" -> INSTAGRAM
            else -> ETC
        }
    }
}