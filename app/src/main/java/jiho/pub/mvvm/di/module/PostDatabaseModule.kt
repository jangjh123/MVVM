package jiho.pub.mvvm.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jiho.pub.mvvm.model.internal.PostDao
import jiho.pub.mvvm.model.internal.PostDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDatabaseModule {

    @Singleton
    @Provides
    fun providePostDatabase(@ApplicationContext context: Context): PostDatabase {
        return Room
            .databaseBuilder(
                context,
                PostDatabase::class.java,
                PostDatabase.DATABASE_NAME
            ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostDao(postDatabase: PostDatabase): PostDao {
        return postDatabase.getPostDao()
    }
}