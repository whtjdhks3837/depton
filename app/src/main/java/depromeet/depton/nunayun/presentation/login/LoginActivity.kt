package depromeet.depton.nunayun.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import depromeet.depton.nunayun.Application
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityLoginBinding
import depromeet.depton.nunayun.model.Question
import depromeet.depton.nunayun.presentation.base.BaseActivity
import depromeet.depton.nunayun.presentation.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import org.json.JSONObject
import com.google.gson.JsonParser
import depromeet.depton.nunayun.model.KakaoToken


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val resourceId: Int = R.layout.activity_login

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()

        viewModel.postUser.observe(this, Observer {
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(Application.appContext, "error", Toast.LENGTH_SHORT)
                .show()
        })
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
            Toast.makeText(
                Application.appContext, "onSessionOpenFailed ${exception?.errorType}",
                Toast.LENGTH_SHORT
            ).show()
            exception?.printStackTrace()
        }

        override fun onSessionOpened() {
            viewModel.postUser(KakaoToken(Session.getCurrentSession().tokenInfo.accessToken))
        }
    }
}