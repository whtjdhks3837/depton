package depromeet.depton.nunayun.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kakao.kakaolink.v2.KakaoLinkResponse
import depromeet.depton.nunayun.presentation.base.BaseViewModel
import depromeet.depton.nunayun.util.KakaoUtil

class HomeViewModel : BaseViewModel() {

    private val _invite = MutableLiveData<KakaoLinkResponse>()

    private val _error = MutableLiveData<String>()

    val invite: LiveData<KakaoLinkResponse> = _invite

    val error: LiveData<String> = _error

    fun sendInvite() {
        KakaoUtil.sendLink()
            .subscribe({
                _invite.value = it
            }, {
                it.printStackTrace()
                _error.value = "error"
            }).bind()
    }
}

class HomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}