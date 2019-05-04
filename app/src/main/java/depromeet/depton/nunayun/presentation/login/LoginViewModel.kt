package depromeet.depton.nunayun.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import depromeet.depton.nunayun.model.KakaoToken
import depromeet.depton.nunayun.model.Question
import depromeet.depton.nunayun.presentation.base.BaseViewModel
import depromeet.depton.nunayun.repository.LoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {

    private val _postUser = MutableLiveData<ResponseBody>()

    private val _error = MutableLiveData<String>()

    val postUser: LiveData<ResponseBody> = _postUser

    val error: LiveData<String> = _error

    fun postUser(token: KakaoToken) {
        repository.postUsers(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _postUser.value = it
            }, {
                _error.value = "error"
            }).bind()
    }
}