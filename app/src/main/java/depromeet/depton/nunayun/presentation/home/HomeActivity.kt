package depromeet.depton.nunayun.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.auth.Session
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityHomeBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity
import depromeet.depton.nunayun.presentation.quiz.QuizActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val resourceId: Int = R.layout.activity_home

    private val viewModel: HomeViewModel by viewModel()

    private val token = Session.getCurrentSession().tokenInfo.accessToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.inviteBtn.setOnClickListener {
            viewModel.sendInvite(token)
        }

        viewModel.user.observe(this, Observer {
            viewModel.waitAccept(token, it.currentInviteId)
        })

        viewModel.invite.observe(this, Observer {
            binding.title.text = "친구의 수락을\n 기다리는 중입니다"
            binding.inviteText.text = "초대 취소하기"
            binding.inviteBtn.visibility = View.INVISIBLE
            binding.centerImg.setImageResource(R.drawable.invite_02)
            viewModel.getInviteId()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this@HomeActivity, "error", Toast.LENGTH_SHORT)
                .show()
        })

        startActivity(Intent(this, QuizActivity::class.java))
    }
}
