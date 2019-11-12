package com.android.kotlinbaseproject.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.android.kotlinbaseproject.data.source.remote.APIService
import com.android.kotlinbaseproject.data.source.remote.ApiErrorHandle
import com.android.kotlinbaseproject.util.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private val TIME_OUT = 60L
//val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.UK)
val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get()) }

    single { createOkHttpClient(get()) }

    single { createMoshiConverterFactory() }

    single { createMoshi() }

}


fun createRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}


fun createOkHttpClient(context: Context): OkHttpClient {
    val client = OkHttpClient.Builder()
        //  .cache(cache)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    client.addNetworkInterceptor(interceptor)
    client.addInterceptor { chain ->
        val request = chain.request().newBuilder()
            //  .headers(getJsonHeader(PrefUtils.getToken(context = context)))
            .build()
        chain.proceed(request)
    }
        .build()
    return client.build()
}

private fun getJsonHeader(authToken: String?): Headers {
    val builder = Headers.Builder()
    builder.add("Content-Type", "application/json")
    builder.add("Accept", "application/json")
    builder.add("Accept-Language", "fa-ir")
    if (authToken != null && authToken.isNotEmpty()) {
        builder.add("Authorization", "JWT $authToken")
    }
    return builder.build()
}

fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}

fun createApiErrorHandle(): ApiErrorHandle {
    return ApiErrorHandle()
}

fun provideIsNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}


fun createService(retrofit: Retrofit): APIService {
    return retrofit.create(APIService::class.java)
}
