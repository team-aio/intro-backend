package aio.intro.domain.user

import aio.intro.domain.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache

@NaturalIdCache
@Entity
@Table(
    name = "user_event_log", indexes = [
        Index(name = "idx_user_event_log_identifier", columnList = "identifier")
    ]
)
class UserEventLog(
    @NaturalId
    val identifier: String,
    val serviceName: String,
    @Enumerated(EnumType.STRING) val eventType: EventType,
    @Column(nullable = true) val metadata: String? = null,
) : AbstractEntity()