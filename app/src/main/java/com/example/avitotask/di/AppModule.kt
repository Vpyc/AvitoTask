package com.example.avitotask.di

import android.content.Context
import com.example.avitotask.utils.ResourceProvider
import com.example.avitotask.utils.impl.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }
}
