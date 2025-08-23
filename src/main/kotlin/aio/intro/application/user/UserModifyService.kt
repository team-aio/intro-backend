package aio.intro.application.user

import aio.intro.application.user.provided.UserAction
import aio.intro.application.user.provided.UserFinder
import aio.intro.application.user.required.UserRepository
import aio.intro.domain.user.User
import aio.intro.domain.user.UserEnterIntroRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class UserModifyService(
    private val userFinder: UserFinder,
    private val userRepository: UserRepository,
) : UserAction {
    override fun enterIntro(request: UserEnterIntroRequest) {
        if (userFinder.existsBy(request.identifier)) {
            activateUser(request.identifier)
            return
        }

        createUser(request)
    }


    private fun activateUser(identifier: String) {
        val user = userFinder.findBy(identifier)

        user.activate()
        userRepository.save(user)
    }

    private fun createUser(request: UserEnterIntroRequest) {
        val user = User.register(request)
        userRepository.save(user)
    }
}