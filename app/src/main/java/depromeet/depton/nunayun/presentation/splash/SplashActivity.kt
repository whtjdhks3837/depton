package depromeet.depton.nunayun.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.presentation.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Thread(Runnable {
            Thread.sleep(500)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }).start()
    }
}
