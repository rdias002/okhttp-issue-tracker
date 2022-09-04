package com.example.tracker.di

import android.app.Application
import androidx.room.Room
import com.example.tracker.data.local.Converters
import com.example.tracker.data.local.IssuesDao
import com.example.tracker.data.local.IssuesDatabase
import com.example.tracker.data.remote.OkHttpApi
import com.example.tracker.data.repository.IssuesRepositoryImpl
import com.example.tracker.data.util.GsonParser
import com.example.tracker.domain.repository.IssuesRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IssuesModule {

    @Provides
    @Singleton
    fun provideOkHttpApi(): OkHttpApi {
        return Retrofit.Builder()
            .baseUrl(OkHttpApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OkHttpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIssueDatabase(app: Application): IssuesDatabase {
        return Room.databaseBuilder(
            app,
            IssuesDatabase::class.java,
            "issues_db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideIssueDao(db: IssuesDatabase): IssuesDao = db.issuesDao

    @Provides
    fun provideIssueRepository(api: OkHttpApi, dao: IssuesDao): IssuesRepository {
        return IssuesRepositoryImpl(api, dao)
    }
}