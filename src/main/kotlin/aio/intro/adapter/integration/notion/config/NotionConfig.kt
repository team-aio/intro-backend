package aio.intro.adapter.integration.notion.config

import aio.intro.adapter.integration.notion.NotionProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
@EnableConfigurationProperties(NotionProperties::class)
class NotionConfig {

    @Bean
    fun notionRestClient(builder: RestClient.Builder, properties: NotionProperties) =
        builder
            .baseUrl(properties.baseUrl)
            .defaultHeader("Authorization", "Bearer ${properties.apiKey}")
            .defaultHeader("Notion-Version", properties.version)
            .build()
}