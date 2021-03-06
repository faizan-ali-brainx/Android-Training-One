package com.brainx.androidbase.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.brainx.androidbase.base.BxBaseViewModel
import com.brainx.androidbase.models.AppException
import com.brainx.androidbase.network.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

fun <T> BxBaseViewModel.request(
    block: suspend () -> Response<T>,
    resultState: (ResultState<T>, ) -> Unit,
    isShowDialog: Boolean = true,
    loadingMessage: String = "",
    resultState1: MutableLiveData<String> = MutableLiveData(),

    ): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) showProcessingLoader(loadingMessage)
            block()
        }.onSuccess {
            hideProcessingLoader()
            resultState1.parseHeader(it)
            resultState(it.paresResult(it))
        }.onFailure {
            hideProcessingLoader()
            resultState(paresException(it))
        }
    }
}

suspend fun <T> request(
    block: suspend () -> Response<T>,
    resultState: (ResultState<T>, ) -> Unit,
    resultState1: MutableLiveData<String> = MutableLiveData(),
){
    runCatching {
        block()
    }.onSuccess {
        resultState1.parseHeader(it)
        resultState(it.paresResult(it))
    }.onFailure {
        resultState(paresException(it))
    }

}


fun <T> getErrorBody(result: Response<T>): AppException {

    if (result.errorBody() != null) {
        return try {
            val obj = JSONObject(result.errorBody()!!.string())
            AppException(
                result.code(),
                obj.getString(ExceptionHandle.error_name), obj.getString(ExceptionHandle.error_name)
            )

        } catch (exception: Exception) {
            AppException(
                result.code(),
                result.message(), result.message()
            )
        }
    } else {
        return AppException(
            result.code(),
            result.message(), result.message()
        )
    }
}
