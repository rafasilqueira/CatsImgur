package br.com.netpos.smartpos.interfaces

import retrofit2.Response

interface IErrorCode<T> {
    fun onSuccessfulResponse(response: T)
    fun onDefaultResponse(response: Response<T>) {}

    fun onConflict(response: Response<T>) {
        onDefaultResponse(response)
    }

    fun onNotFound(response: Response<T>) {
        onDefaultResponse(response)
    }

    fun onBadRequest(response: Response<T>) {
        onDefaultResponse(response)
    }

    fun onForbidden(response: Response<T>) {
        onDefaultResponse(response)
    }

    fun onNullResponse(response: Response<T>) {
        onDefaultResponse(response)
    }
}