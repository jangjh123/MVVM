package jiho.pub.mvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jiho.pub.mvvm.model.data.Match
import jiho.pub.mvvm.model.external.ApiService
import jiho.pub.mvvm.viewModel.repository.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val useCase: NetworkUseCase,
    dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private val list: MutableLiveData<List<Match>> = MutableLiveData()
    private val scope = CoroutineScope(dispatcher)

    fun getMatchList(): LiveData<List<Match>> = list

    fun loadData() {
        useCase.invoke(scope, "VkSp47EyJkcvC7F8tjag", "1008976901424", {
            Log.d("onSuccess", it.size.toString())
        }, {
            Log.d("onFailure", it.toString())
        })
    }
}