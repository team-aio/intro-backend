package aio.intro.domain.log

import aio.intro.domain.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache
import java.time.LocalDateTime

@NaturalIdCache
@Entity
@Table(
    name = "user_event_log", indexes = [
        Index(name = "idx_user_event_log_identifier", columnList = "identifier")
    ]
)
class UserEventLog private constructor(
    @NaturalId
    val identifier: String,
    val serviceName: String,
    createdAt: LocalDateTime,
    @Enumerated(EnumType.STRING) val eventType: EventType,
    @Column(nullable = true) val metadata: String? = null,
    @Column(name = "device_type") @Enumerated(EnumType.STRING) val deviceType: DeviceType,
    @Column(name = "media_type") @Enumerated(EnumType.STRING) val mediaType: MediaType
) : AbstractEntity(createdAt = createdAt) {
    companion object {
        fun create(
            identifier: String,
            serviceName: String,
            eventType: EventType,
            metaData: String? = null,
            deviceType: DeviceType,
            mediaType: MediaType,
            createdAt: LocalDateTime = LocalDateTime.now()
        ) = UserEventLog(identifier, serviceName, createdAt, eventType, metaData, deviceType, mediaType)
    }

    fun isFirstVisit(): Boolean = this.eventType == EventType.FIRST_VISIT

    fun isEmailButtonClick(): Boolean {
        return this.eventType == EventType.BUTTON_CLICK
                && this.metadata?.contains("email") == true
    }
}