package depromeet.depton.nunayun.presentation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityResultBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    override val resourceId: Int = R.layout.activity_result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
    }

    private fun bindView() {

    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, ResultActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}
