package com.patricioglenn.cafeto_admin.network

import java.lang.Exception

interface Callback<T> {

    fun onSuccess(result: T?)
    fun onFailed(exception: Exception)

}