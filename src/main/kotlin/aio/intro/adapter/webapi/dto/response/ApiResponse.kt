package aio.intro.adapter.webapi.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@JsonPropertyOrder("timestamp", "status", "detail", "data")
class ApiResponse<T> private constructor(
    val timestamp: LocalDateTime,
    val status: Int,
    val detail: String?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T? = null,
) {
    companion object {
        fun <T> ok(detail: String? = null, data: T? = null): ApiResponse<T> =
            ApiResponse(LocalDateTime.now(), HttpStatus.OK.value(), detail)

        fun <T> created(detail: String? = null, data: T? = null): ApiResponse<T> =
            ApiResponse(LocalDateTime.now(), HttpStatus.CREATED.value(), detail, data)
    }
}
