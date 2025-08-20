package aio.intro.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_event_log")
class UserEventLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val identifier: String,
    val serviceName: String,
    @Enumerated(EnumType.STRING) val eventType: EventType,
    @Column(nullable = true) val metadata: String? = null,
)