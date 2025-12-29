package com.jslps.empvisist.di

import com.jslps.empvisist.data.local.repository.GetPanchayatListImpl
import com.jslps.empvisist.data.local.repository.GetUserDetailsImpl
import com.jslps.empvisist.data.local.repository.VillageImpl
import com.jslps.empvisist.data.remote.repository.LoginRepositoryImpl
import com.jslps.empvisist.domain.repository.GetPanchayatListRepository
import com.jslps.empvisist.domain.repository.GetUserDetailsRepository
import com.jslps.empvisist.domain.repository.LoginRepository
import com.jslps.empvisist.domain.repository.VillageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindPanchayatListRepository(
        impl: GetPanchayatListImpl
    ): GetPanchayatListRepository

    @Binds
    @Singleton
    abstract fun bindUserDetailsRepository(
        impl: GetUserDetailsImpl
    ): GetUserDetailsRepository

    @Binds
    @Singleton
    abstract fun bindVillageRepository(
        impl: VillageImpl
    ): VillageRepository
}