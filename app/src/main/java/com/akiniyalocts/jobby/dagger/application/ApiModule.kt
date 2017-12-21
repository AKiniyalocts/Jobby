package com.akiniyalocts.jobby.dagger.application

import com.akiniyalocts.jobby.model.GithubApi
import com.akiniyalocts.jobby.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
            Retrofit.Builder()
                    .baseUrl(GithubApi.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .client(client)
                    .build()

    @Provides
    @Singleton
    fun providerGithubApi(retrofit: Retrofit) = retrofit.create(GithubApi::class.java)

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun gsonConverter(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun httpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{

        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(loggingInterceptor)

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