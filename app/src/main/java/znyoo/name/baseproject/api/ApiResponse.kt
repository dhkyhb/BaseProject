package znyoo.name.baseproject.api

import retrofit2.Response

sealed class ApiResponse<R> {

    companion object {

        fun <R> create(response: Response<R>): ApiResponse<R> = if (response.isSuccessful) {
            val body = response.body()
            if (body == null || response.code() == 204) {
                ApiEmptyResponse()
            } else {
                AdiSuccessResponse(body)
            }
        } else {
            val msg = response.errorBody()?.toString()
            val errorMsg = if (msg.isNullOrEmpty()) {
                response.message()
            } else {
                msg
            }

            APiFailureResponse(errorMessage = errorMsg ?: "unknown error")
        }

        fun <R> create(throwable: Throwable): APiFailureResponse<R> =
            APiFailureResponse(errorMessage = throwable.message ?: "unknown error")

    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<R> : ApiResponse<R>()

data class AdiSuccessResponse<R>(val body: R) : ApiResponse<R>()

data class APiFailureResponse<R>(val errorMessage: String) : ApiResponse<R>()