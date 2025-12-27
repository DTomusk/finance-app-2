package com.example.financeapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.financeapp.core.database.AppDatabase
import com.example.financeapp.core.database.migrations.MIGRATION_1_2
import com.example.financeapp.core.di.DatabaseCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseCallback(
        databaseProvider: Provider<AppDatabase>
    ): DatabaseCallback =
        DatabaseCallback(databaseProvider)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        callback: DatabaseCallback
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app.db"
        )
            .addMigrations(MIGRATION_1_2)
            .addCallback(callback)
            // TODO: remove if this doesn't work
            .build()
}