package aio.intro.domain.log

enum class EventType {
    FIRST_VISIT,
    REVISIT,
    EXIT,
    SCROLL,
    BUTTON_CLICK;

    companion object {
        fun from(name: String) = when (name.lowercase()) {
            "first_visit" -> FIRST_VISIT
            "revisit" -> REVISIT
            "exit" -> EXIT
            "scroll" -> SCROLL
            "button_click" -> BUTTON_CLICK
            else -> throw IllegalArgumentException("Unknown event type: $name")
        }
    }
}