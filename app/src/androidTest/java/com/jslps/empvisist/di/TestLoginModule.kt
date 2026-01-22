package com.jslps.empvisist.di

import com.jslps.empvisist.domain.usecase.LoginUseCase
import com.jslps.empvisist.domain.usecase.LoginValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import jakarta.inject.Singleton


@Suppress("unused")
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestLoginModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(): LoginUseCase = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideLoginValidationUseCase(): LoginValidationUseCase = mockk(relaxed = true)

}