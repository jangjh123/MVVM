package jiho.pub.mvvm.viewModel

import android.util.Log
import androidx.annotation.WorkerThread
import jiho.pub.mvvm.model.data.Match
import jiho.pub.mvvm.viewModel.repository.NetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@WorkerThread
class NetworkUseCase @Inject constructor(private val repository: NetworkRepository) {

    fun invoke(
        scope: CoroutineScope,
        key: String,
        uid: String,
        onSuccess: (List<Match>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        scope.launch {

            repository.getAllMatches(key, uid)
                .runCatching(onSuccess).onFailure(onFailure)
        }
    }
}