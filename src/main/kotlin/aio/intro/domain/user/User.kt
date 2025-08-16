package aio.intro.domain.user

import aio.intro.domain.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId
import org.hibernate.annotations.NaturalIdCache

@Entity
@Table(name = "users")
@NaturalIdCache
class User private constructor(
    @NaturalId
    @Column(name = "identifier", unique = true)
    val identifier: String,
    @Column(name = "service_name")
    val serviceName: String,
) : AbstractEntity() {
    var userStatus: UserStatus = UserStatus.ACTIVE
        protected set
}