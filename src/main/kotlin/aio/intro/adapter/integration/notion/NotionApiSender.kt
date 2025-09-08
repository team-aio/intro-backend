package aio.intro.adapter.integration.notion

import aio.intro.adapter.integration.notion.config.NotionProperties
import aio.intro.adapter.integration.notion.dto.CreatePageRequest
import aio.intro.adapter.integration.notion.dto.DatabaseProperties
import aio.intro.adapter.integration.notion.dto.Parent
import aio.intro.adapter.integration.notion.dto.property.DateProperty
import aio.intro.adapter.integration.notion.dto.property.NumberProperty
import aio.intro.adapter.integration.notion.dto.property.RichText
import aio.intro.adapter.integration.notion.dto.property.TextContent
import aio.intro.adapter.integration.notion.dto.property.TitleProperty
import aio.intro.application.log.required.UserActionStatisticsDatabase
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.time.Instant

private const val CREATE_PAGE_API = "/v1/pages"

@Component
class NotionApiSender(
    private val notionRestClient: RestClient,
    private val notionProperties: NotionProperties
) : UserActionStatisticsDatabase {
    override fun addRow(
        id: String,
        serviceName: String,
        visitorCount: Long,
        emailButtonClickCount: Long
    ) {
        callCreatePageAPI(id, serviceName, visitorCount, emailButtonClickCount)
    }

    private fun callCreatePageAPI(
        id: String,
        serviceName: String,
        visitorCount: Long,
        emailButtonClickCount: Long
    ): ResponseEntity<Void> = notionRestClient.post()
        .uri(CREATE_PAGE_API)
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            CreatePageRequest(
                parent = Parent(dataSourceId = notionProperties.dataSourceId),
                properties = DatabaseProperties(
                    id = TitleProperty(title = listOf(RichText(text = TextContent(serviceName + id)))),
                    visitorCount = NumberProperty(number = visitorCount.toDouble()),
                    emailButtonClickCount = NumberProperty(number = emailButtonClickCount.toDouble()),
                    createdTime = DateProperty(createdTime = Instant.now())
                )
            )
        )
        .retrieve()
        .toBodilessEntity()
}