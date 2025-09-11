package aio.intro.adapter.batch.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@Configuration
@EnableScheduling
class SchedulerConfig {
    
    @Bean
    fun taskScheduler(): TaskScheduler {
        return ThreadPoolTaskScheduler()
            .apply {
                poolSize = 1 // 스케쥴러 풀 사이즈 설정 (스케쥴러가 늘어날때마다 추가하기)
                setThreadNamePrefix("ThreadPoolTaskScheduler")
            }
    }
}