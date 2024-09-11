package com.example.avitotask.di

import com.example.avitotask.repository.ProductRepository
import com.example.avitotask.repository.UserRepository
import com.example.avitotask.repository.impl.ProductRepositoryImpl
import com.example.avitotask.repository.impl.UserRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideUserRepository(gson: Gson): UserRepository {
        return UserRepositoryImpl(gson)
    }

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryImpl()
    }
}