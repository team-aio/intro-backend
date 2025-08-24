package aio.intro.domain.log

enum class DeviceType {
    MOBILE,
    PC,
    ETC;

    companion object {
        fun from(name: String) = when (name.lowercase()) {
            "mobile" -> MOBILE
            "pc" -> PC
            else -> ETC
        }
    }
}