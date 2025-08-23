package aio.intro.application.user.required

import aio.intro.domain.user.User
import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, Long> {
    fun save(user: User): User
    fun findById(id: Long): User?
    fun findByIdentifier(identifier: String): User?
    fun existsByIdentifier(identifier: String): Boolean
}