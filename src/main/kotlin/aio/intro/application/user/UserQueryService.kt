package aio.intro.application.user

import aio.intro.application.user.provided.UserFinder
import aio.intro.application.user.required.UserRepository
import aio.intro.domain.user.User
import org.springframework.stereotype.Service

@Service
class UserQueryService(
    private val userRepository: UserRepository,
) : UserFinder {
    override fun findBy(identifier: String): User {
        return userRepository.findByIdentifier(identifier)
            ?: throw IllegalArgumentException("유저를 찾을 수 없습니다.")
    }
}