package aio.intro.adapter.integration.notion.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RowProperties(
    @JsonProperty("id") val id: TitleProperty,
    @JsonProperty("방문자 수") val visitorCount: NumberProperty,
    @JsonProperty("이메일 버튼 클릭 수") val emailButtonClickCount: NumberProperty,
    @JsonProperty("Created Time") val createdTime: DateProperty,
)
