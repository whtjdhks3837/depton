package depromeet.depton.nunayun.presentation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityResultBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    private val viewModel: ResultViewModel by viewModel()
    override val resourceId: Int = R.layout.activity_result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun bindView() {
        for (i in 0..9) {
            linear_result_matching.addView(
                LayoutInflater.from(this).inflate(
                    R.layout.item_result,
                    linear_result_matching,
                    false
                )
            )
        }
    }

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(
                context,
                ResultActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}
