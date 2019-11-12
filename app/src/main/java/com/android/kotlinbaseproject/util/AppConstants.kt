package com.android.kotlinbaseproject.util

class AppConstants{
    companion object {

        const val API_BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val API_USER_TOKEN = "be98e3602adc466b92db02e7f59cc5c0"
        const val API_COUNTRY = "us"
        const val API_CATEGORY = "=business"
        const val DEFAULT_LANGUAGE = "fa"
        const val RESPONSE_REQUEST_400 = 400

    }

    enum class API_RESPONSE_MESSAGES{
        SERVER_ERROR,
        BAD_REQUEST
    }
}