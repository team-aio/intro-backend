package aio.intro.adapter.webapi.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice(basePackages = ["aio.intro.adapter.webapi"])
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ProblemDetail {
        return getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception)
    }

    private fun getProblemDetail(status: HttpStatus, ex: Exception): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(
            status,
            ex.message
        )

        problemDetail.setProperty("timestamp", LocalDateTime.now())
        problemDetail.setProperty("exception", ex.javaClass.simpleName)

        return problemDetail
    }
}