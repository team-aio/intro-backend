package aio.intro.domain

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class AbstractEntity protected constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @CreatedDate
    @Column(updatable = false)
    val createdAt: LocalDateTime,
) {

    @LastModifiedDate
    var updated_at: LocalDateTime = LocalDateTime.now()
        protected set
}