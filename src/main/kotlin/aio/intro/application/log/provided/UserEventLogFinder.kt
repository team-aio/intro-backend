package aio.intro.application.log.provided

interface UserEventLogFinder {
    fun existsBy(identifier: String, serviceName: String): Boolean
}