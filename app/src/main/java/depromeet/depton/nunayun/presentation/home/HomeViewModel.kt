package depromeet.depton.nunayun.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakao.auth.Session
import depromeet.depton.nunayun.model.User
import depromeet.depton.nunayun.presentation.base.BaseViewModel
import depromeet.depton.nunayun.repository.HomeRepository
import depromeet.depton.nunayun.util.KakaoUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.concurrent.TimeUnit

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val _user = MutableLiveData<User>()

    private val _invite = MutableLiveData<ResponseBody>()

    private val _accept = MutableLiveData<ResponseBody>()

    private val _error = MutableLiveData<String>()

    val user: LiveData<User> = _user

    val invite: LiveData<ResponseBody> = _invite

    val accept: LiveData<ResponseBody> = _accept

    val error: LiveData<String> = _error

    fun getInviteId() {
        KakaoUtil.requestAccessTokenInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                repository.getUsers(Session.getCurrentSession().tokenInfo.accessToken, it.userId.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ user ->
                        _user.value = user
                    }, {
                        it.printStackTrace()
                        _error.value = "error"
                    })
            }, {
                it.printStackTrace()
                _error.value = "error"
            }).bind()
    }

    fun sendInvite(token: String) {
        KakaoUtil.sendLink()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                repository.postInvites(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _invite.value = it
                    }, {
                        it.printStackTrace()
                        _error.value = "error"
                    })
            }, {
                it.printStackTrace()
                _error.value = "error"
            }).bind()
    }

    fun waitAccept(token: String, inviteId: String) {
        repository.postInviteAccept(token, inviteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .repeatWhen { it.delay(5, TimeUnit.SECONDS) }
            .subscribe({
                _accept.value = it
            }, {
                it.printStackTrace()
            }).bind()
    }
}