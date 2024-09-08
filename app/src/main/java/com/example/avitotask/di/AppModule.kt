package com.example.avitotask.di

import android.content.Context
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.shared.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }
}