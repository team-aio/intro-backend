package aio.intro.adapter.integration.notion.dto.property

data class NumberProperty(
    val type: String = "number",
    val number: Double,
)