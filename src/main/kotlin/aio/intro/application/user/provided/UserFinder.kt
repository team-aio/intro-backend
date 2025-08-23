package aio.intro.application.user.provided

import aio.intro.domain.user.User

/**
 * 유저 쿼리와 관련된 기능을 제공
 */
interface UserFinder {
    fun findBy(identifier: String): User
    fun existsBy(identifier: String): Boolean
}