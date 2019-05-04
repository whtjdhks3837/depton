package depromeet.depton.nunayun.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import depromeet.depton.nunayun.Application
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityLoginBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity
import depromeet.depton.nunayun.presentation.home.HomeActivity
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val resourceId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private val callback = object : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            Timber.d("onSessionOpenFailed")
            Toast.makeText(Application.appContext, "onSessionOpenFailed ${exception?.errorType}",
                Toast.LENGTH_SHORT).show()
            exception?.printStackTrace()
        }

        override fun onSessionOpened() {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
    }
}