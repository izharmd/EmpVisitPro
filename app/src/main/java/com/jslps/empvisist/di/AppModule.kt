package com.jslps.empvisist.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.jslps.compose.utils.AppConstant
import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.local.dao.AppDatabase
import com.jslps.empvisist.data.local.repository.GetUserDetailsImpl
import com.jslps.empvisist.domain.repository.GetUserDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppConstant.dbName
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase): AppDao {
        return db.getDao()
    }


    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("app_preferences")
        }
    }

    // Repository bindings
   /* @Singleton
    @Provides
    fun providePanchayatListRepository(impl: GetPanchayatListImpl): GetPanchayatListRepository {
        return impl
    }*/

    /*@Singleton
    @Provides
    fun provideUserDetailsRepository(impl: GetUserDetailsImpl): GetUserDetailsRepository {
        return impl
    }*/
}
