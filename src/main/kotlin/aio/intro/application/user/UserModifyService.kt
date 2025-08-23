package aio.intro.application.user

import aio.intro.application.user.provided.UserRegister
import aio.intro.application.user.required.UserRepository
import aio.intro.domain.user.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Transactional
class UserModifyService(
    private val userRepository: UserRepository,
) : UserRegister {
    override fun register(identifier: String, serviceName: String) {
        val newUser = User.create(identifier, serviceName, LocalDateTime.now())
        userRepository.save(newUser)
    }

    override fun activate(identifier: String) {
        TODO("Not yet implemented")
    }
}