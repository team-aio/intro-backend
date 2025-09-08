package aio.intro.adapter.integration.notion.dto

import aio.intro.adapter.integration.notion.dto.property.DateProperty
import aio.intro.adapter.integration.notion.dto.property.NumberProperty
import aio.intro.adapter.integration.notion.dto.property.TitleProperty
import com.fasterxml.jackson.annotation.JsonProperty

data class CreatePageRequest(
    @JsonProperty("parent") val parent: Parent,
    @JsonProperty("properties") val properties: DatabaseProperties
)

data class Parent(
    val type: String = "data_source_id",
    @JsonProperty("data_source_id")
    val dataSourceId: String
)

data class DatabaseProperties(
    @JsonProperty("id") val id: TitleProperty,
    @JsonProperty("방문자 수") val visitorCount: NumberProperty,
    @JsonProperty("이메일 버튼 클릭 수") val emailButtonClickCount: NumberProperty,
    @JsonProperty("Created Time") val createdTime: DateProperty,
)
