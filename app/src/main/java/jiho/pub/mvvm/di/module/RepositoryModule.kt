package jiho.pub.mvvm.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import jiho.pub.mvvm.model.internal.PostDao
import jiho.pub.mvvm.viewModel.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMainRepository(postDao: PostDao, dispatcher: CoroutineDispatcher): MainRepository {
        return MainRepository(postDao, dispatcher)
    }
}