package jiho.pub.mvvm.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import jiho.pub.mvvm.viewModel.NetworkUseCase
import jiho.pub.mvvm.viewModel.repository.NetworkRepository
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideNetworkUseCase(
        repository: NetworkRepository
    ) = NetworkUseCase(repository)
}