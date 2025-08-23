package aio.intro.application.user

import aio.intro.application.user.provided.UserAction
import aio.intro.application.user.provided.UserFinder
import aio.intro.application.user.required.UserRepository
import aio.intro.domain.user.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Transactional
class UserModifyService(
    private val userFinder: UserFinder,
    private val userRepository: UserRepository,
) : UserAction {
    override fun enterIntro(identifier: String, serviceName: String) {
        if (userFinder.existsBy(identifier)) {
            activateUser(identifier)
            return
        }

        createUser(identifier, serviceName)
    }


    private fun activateUser(identifier: String) {
        val user = userFinder.findBy(identifier)

        user.activate()
        userRepository.save(user)
    }

    private fun createUser(identifier: String, serviceName: String) {
        val user = User.create(identifier, serviceName, LocalDateTime.now())
        userRepository.save(user)
    }
}