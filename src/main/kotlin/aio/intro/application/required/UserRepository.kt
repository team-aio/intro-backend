package aio.intro.application.required

import aio.intro.domain.user.User
import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, Long> {
    fun save(user: User): User
    fun findById(id: Long): User?
}