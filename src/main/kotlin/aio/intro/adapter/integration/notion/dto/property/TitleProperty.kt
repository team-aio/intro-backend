package aio.intro.adapter.integration.notion.dto.property

data class TitleProperty(val type: String = "title", val title: List<RichText>)
data class RichText(val type: String = "text", val text: TextContent)
data class TextContent(val content: String)
