package com.app.assignmentapp.di

import com.app.assignmentapp.data.Repository
import com.app.assignmentapp.data.remote.NetworkRepository
import com.app.assignmentapp.data.remote.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(value = [SingletonComponent::class, FragmentComponent::class])
abstract class AppModule  {

    @Binds
    abstract fun bindNetworkRepository( repository: NetworkRepositoryImpl): NetworkRepository

}