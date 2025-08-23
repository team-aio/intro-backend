package aio.intro.domain.user

import aio.intro.domain.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@NaturalIdCache
class User private constructor(
    @NaturalId
    @Column(name = "identifier", unique = true)
    val identifier: String,
    @Column(name = "service_name")
    val serviceName: String,

    createdAt: LocalDateTime,
) : AbstractEntity(createdAt = createdAt) {
    var userStatus: UserStatus = UserStatus.ACTIVE
        protected set

    companion object {
        fun create(identifier: String, serviceName: String, createdAt: LocalDateTime) =
            User(identifier, serviceName, createdAt)

        fun register(request: UserEnterIntroRequest) =
            User(request.identifier, request.serviceName, LocalDateTime.now())
    }

    fun activate() {
        check(this.userStatus == UserStatus.INACTIVE) { "이미 활성화된 유저입니다." }

        this.userStatus = UserStatus.ACTIVE
    }

    fun deactivate() {
        check(this.userStatus == UserStatus.ACTIVE) { "이미 비활성화된 유저입니다." }

        this.userStatus = UserStatus.INACTIVE
    }
}