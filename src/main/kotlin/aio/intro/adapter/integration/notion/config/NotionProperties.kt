package aio.intro.adapter.integration.notion.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("notion")
data class NotionProperties(
    val baseUrl: String,
    val apiKey: String,
    val databaseId: String,
    val dataSourceId: String,
    val version: String = "2022-06-28"
)