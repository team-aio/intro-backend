package aio.intro.adapter.integration.notion.dto

import java.time.Instant

data class DateProperty(
    val type: String = "created_time",
    val createdTime: Instant = Instant.now()
)