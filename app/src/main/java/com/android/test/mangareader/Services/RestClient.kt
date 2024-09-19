package com.android.test.mangareader.Services

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.UnsupportedOperationException
import java.util.concurrent.TimeUnit


object RestClient {

    private var tokenWithBearer: String = "" //  In Memory token
    private lateinit var gson: Gson
    private lateinit var cache: Cache
    private lateinit var retrofit: Retrofit
    private lateinit var httpClient: OkHttpClient
    private var SIZE_OF_CACHE = (5 * 1024 * 1024 // 5 MB
            ).toLong()
    fun initRestConfig(context: Context, baseURL: String){
        cache = Cache(context.cacheDir, SIZE_OF_CACHE)
        initHttpClient(context)
        initGson()
        initRetrofit(baseURL)
    }

    private fun initGson(){
            gson = GsonBuilder()
                .setLenient()
                .addSerializationExclusionStrategy(NoModuleExclusionStrategy(false))
                .addDeserializationExclusionStrategy(NoModuleExclusionStrategy(true))
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
    }

    fun <S> create(serviceClass: Class<S>): S{
            if (!RestClient::retrofit.isInitialized){
                throw UnsupportedOperationException("Retrofit not initialized")
            }
        return retrofit.create(serviceClass)
    }

    fun getRetrofit(): Retrofit{
        return retrofit
    }

    fun refreshToken(token: String?){
        this.tokenWithBearer =token ?: ""
    }
    private fun initHttpClient(context: Context){
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            logging.level =HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val networkInterceptor = Interceptor{ chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                                .header("Accept", "application/json")
                                .header("Content-Type", "application/json" )
                                .header("X-Requested-With", "XMLHttpRequest")
                                .header("client-id","manga-reader-app")
                                .header("Authorization", tokenWithBearer)
                                .method(original.method, original.body)

            if (hasNetwork(context)){
                /*
            *  If there is Internet, get the cache that was stored 5 seconds ago.
            *  If the cache is older than 5 seconds, then discard it,
            *  and indicate an error in fetching the response.
            *  The 'max-age' attribute is responsible for this behavior.
            */
                requestBuilder.header("Cache-Control", "public, max-age=" + 5)
            } else {
                /*
                *  If there is no Internet, get the cache that was stored 7 days ago.
                *  If the cache is older than 7 days, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-stale' attribute is responsible for this behavior.
                *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                */
                requestBuilder.header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                )
            }
            val request: Request = requestBuilder.build()
            val response = chain.proceed(request)

            if (response.code == 504 || response.code == 503){
                throw IOException("Server Unreachable")
            }
            response
        }

            httpClient = OkHttpClient.Builder()
                .connectTimeout(240, TimeUnit.SECONDS)
                .readTimeout(240, TimeUnit.SECONDS)
                .writeTimeout(240, TimeUnit.SECONDS)
                .callTimeout(240, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(networkInterceptor)
                .cache(cache)
                .build()
    }

    private fun hasNetwork(context: Context): Boolean{
        return try {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(
                   Context.CONNECTIVITY_SERVICE
                        ) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null&& activeNetworkInfo.isConnected
        } catch (e: Exception){
            true
        }
    }

    private fun initRetrofit(baseURL: String){
        retrofit =  Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(RetrofitConvertorFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }
}