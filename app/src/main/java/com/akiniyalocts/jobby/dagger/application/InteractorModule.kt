package com.akiniyalocts.jobby.dagger.application

import com.akiniyalocts.jobby.model.GithubApi
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailInteractor
import com.akiniyalocts.jobby.ui.jobdetail.imp.JobDetailInteractorImp
import com.akiniyalocts.jobby.ui.joblisting.JobListInteractor
import com.akiniyalocts.jobby.ui.joblisting.imp.JobListInteractorImp
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
@Module
class InteractorModule {

    @Singleton
    @Provides
    fun provideJobListInteractor(githubApi: GithubApi, fusedLocationProviderClient : FusedLocationProviderClient) : JobListInteractor = JobListInteractorImp(githubApi, fusedLocationProviderClient)

    @Singleton
    @Provides
    fun providerJobDetailInteractor(): JobDetailInteractor = JobDetailInteractorImp()
}