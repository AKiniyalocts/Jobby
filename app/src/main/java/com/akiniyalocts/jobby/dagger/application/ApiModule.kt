package com.akiniyalocts.jobby.dagger.application

import com.akiniyalocts.jobby.BuildConfig
import com.akiniyalocts.jobby.dagger.JobbyApplication
import com.akiniyalocts.jobby.model.GithubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provide any api related dependencies needed.
 *
 */
@Module
class ApiModule(private val app:JobbyApplication) {

    /**
     * Retrofit singleton for creating api's
     */
    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(GithubApi.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .client(client)
                    .build()

    /**
     * Github api singleton
     */
    @Provides
    @Singleton
    fun providerGithubApi(retrofit: Retrofit) = retrofit.create(GithubApi::class.java)

    /**
     * Gson Serializer singleton
     */
    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    /**
     * GsonConverterFactory for retrofit deserialization
     */
    @Provides
    @Singleton
    fun gsonConverter(gson: Gson) = GsonConverterFactory.create(gson)


    /**
     * HttpClient
     */
    @Provides
    @Singleton
    fun httpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{

        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        val cacheSize : Long = 50 * 1024 * 1024 // 50MB


        clientBuilder.addInterceptor(loggingInterceptor)

        clientBuilder.cache(Cache(app.cacheDir, cacheSize))

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun loggingInterceptor():HttpLoggingInterceptor{

        val loggingInterceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        }

        return loggingInterceptor
    }
}