package depromeet.depton.nunayun.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.ContentObject
import com.kakao.message.template.FeedTemplate
import com.kakao.message.template.LinkObject
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityHomeBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity
import depromeet.depton.nunayun.util.KakaoUtil
import timber.log.Timber

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val resourceId: Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, HomeViewModelFactory())
            .get(HomeViewModel::class.java)

        binding.inviteBtn.setOnClickListener {
            viewModel.sendInvite()
        }

        viewModel.invite.observe(this, Observer {
            Toast.makeText(this@HomeActivity, "invite", Toast.LENGTH_SHORT)
                .show()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this@HomeActivity, "error", Toast.LENGTH_SHORT)
                .show()
        })
    }
}
