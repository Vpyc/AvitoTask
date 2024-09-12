package com.example.avitotask.di

import android.content.Context
import com.example.avitotask.shared.TokenManager
import com.example.avitotask.shared.impl.TokenManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SecurityModule {

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManagerImpl(context)
    }
}