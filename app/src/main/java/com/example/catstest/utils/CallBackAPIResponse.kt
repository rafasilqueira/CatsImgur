@file:Suppress("DEPRECATION")

package com.example.catstest.utils

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import br.com.netpos.smartpos.interfaces.IErrorCode
import com.example.catstest.R
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection.*

/**
 * Created by Rafael.Tupinambá on 09/01/2020.
 */

abstract class CallBackAPIResponse<T>(
    private val context: Context,
    showDialog: Boolean,
    private val showToastOnError: Boolean
) : Callback<T>, IErrorCode<T> {

    private var dialog: ProgressDialog = ProgressDialog(context)

    init {
        if (showDialog) {
            dialog.setMessage(context.getString(R.string.load_data))
            dialog.show()
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        dismissDialog()
        if (!response.isSuccessful) when (response.code()) {
            HTTP_CONFLICT -> onConflict(response)
            HTTP_NOT_FOUND -> onNotFound(response)
            HTTP_BAD_REQUEST -> onBadRequest(response)
            HTTP_FORBIDDEN -> onForbidden(response)
            else -> onDefaultResponse(response)
        } else {
            response.body()?.let {
                onSuccessfulResponse(it)
                return@let
            }

            onNullResponse(response)

        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        dismissDialog()
        t.printStackTrace()
        if (showToastOnError) showToastError(context)
    }

    private fun dismissDialog() {
        if (dialog.isShowing) dialog.dismiss()
    }

    private fun showToastError(context: Context) {
        Toast.makeText(context, context.getString(R.string.error_general), LENGTH_SHORT).show()
    }

}