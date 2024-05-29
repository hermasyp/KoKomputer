package com.catnip.kokomputer.utils

import com.catnip.kokomputer.data.model.Response
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
sealed class ResultWrapper<T>(
    val payload: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : ResultWrapper<T>(data)

    class Error<T>(exception: Exception?, data: T? = null) :
        ResultWrapper<T>(data, exception = exception)

    class Empty<T>(data: T? = null) : ResultWrapper<T>(data)

    class Loading<T>(data: T? = null) : ResultWrapper<T>(data)

    class Idle<T>(data: T? = null) : ResultWrapper<T>(data)
}

fun <T> ResultWrapper<T>.proceedWhen(
    doOnSuccess: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnError: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnLoading: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnEmpty: ((resource: ResultWrapper<T>) -> Unit)? = null,
    doOnIdle: ((resource: ResultWrapper<T>) -> Unit)? = null,
) {
    when (this) {
        is ResultWrapper.Success -> doOnSuccess?.invoke(this)
        is ResultWrapper.Error -> doOnError?.invoke(this)
        is ResultWrapper.Loading -> doOnLoading?.invoke(this)
        is ResultWrapper.Empty -> doOnEmpty?.invoke(this)
        is ResultWrapper.Idle -> doOnIdle?.invoke(this)
    }
}

suspend fun <T> ResultWrapper<T>.suspendProceedWhen(
    doOnSuccess: (suspend (resource: ResultWrapper<T>) -> Unit)? = null,
    doOnError: (suspend (resource: ResultWrapper<T>) -> Unit)? = null,
    doOnLoading: (suspend (resource: ResultWrapper<T>) -> Unit)? = null,
    doOnEmpty: (suspend (resource: ResultWrapper<T>) -> Unit)? = null,
    doOnIdle: (suspend (resource: ResultWrapper<T>) -> Unit)? = null,
) {
    when (this) {
        is ResultWrapper.Success -> doOnSuccess?.invoke(this)
        is ResultWrapper.Error -> doOnError?.invoke(this)
        is ResultWrapper.Loading -> doOnLoading?.invoke(this)
        is ResultWrapper.Empty -> doOnEmpty?.invoke(this)
        is ResultWrapper.Idle -> doOnIdle?.invoke(this)
    }
}

fun <T> ResultWrapper<T>.proceed(
    doOnSuccess: ((resource: ResultWrapper<T>) -> Unit),
    doOnError: ((resource: ResultWrapper<T>) -> Unit),
    doOnLoading: ((resource: ResultWrapper<T>) -> Unit),
) {
    when (this) {
        is ResultWrapper.Success -> doOnSuccess.invoke(this)
        is ResultWrapper.Error -> doOnError.invoke(this)
        is ResultWrapper.Loading -> doOnLoading.invoke(this)
        else -> {}
    }
}

suspend fun <T> proceed(block: suspend () -> T): ResultWrapper<T> {
    return try {
        val result = block.invoke()
        if (result is Collection<*> && result.isEmpty()) {
            ResultWrapper.Empty(result)
        } else {
            ResultWrapper.Success(result)
        }
    } catch (e: Exception) {
        ResultWrapper.Error<T>(exception = Exception(e))
    }
}

fun <T> proceedFlow(block: suspend () -> T): Flow<ResultWrapper<T>> {
    return flow<ResultWrapper<T>> {
        val result = block.invoke()
        emit(
            if (result is Collection<*> && result.isEmpty()) {
                ResultWrapper.Empty(result)
            } else {
                ResultWrapper.Success(result)
            },
        )
    }.catch { e ->
        emit(ResultWrapper.Error(exception = e.parseException()))
    }.onStart {
        emit(ResultWrapper.Loading())
    }

}

fun Throwable?.parseException(): Exception {
    when (this) {
        is IOException -> {
            return NoInternetException()
        }
        is HttpException -> {
            try {
                val gson = Gson()
                val errorResponseBody = this.response()?.errorBody()?.string()
                val errorBody = gson.fromJson(errorResponseBody, Response::class.java)
                return ApiErrorException(errorBody)
            } catch (e: Exception) {
                return Exception(e)
            }
        }
        else -> return Exception(this)
    }
}

class ApiErrorException(val errorResponse: Response<*>) : Exception()
class NoInternetException() : Exception()

