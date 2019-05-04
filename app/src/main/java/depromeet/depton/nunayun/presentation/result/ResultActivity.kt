package depromeet.depton.nunayun.presentation.result

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityQuizBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import android.view.animation.DecelerateInterpolator

class ResultActivity : BaseActivity<ActivityQuizBinding>() {
    override val resourceId: Int = R.layout.activity_quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
    }

    private fun bindView() {
        countDown()
    }

    private fun countDown() {
        text_quiz_count.text = "3"
        text_quiz_detail.text = getString(R.string.quiz_count_detail, 3)

        Handler().postDelayed({
            text_quiz_count.text = "2"
            text_quiz_detail.text = getString(R.string.quiz_count_detail, 2)
        }, 1000L)

        Handler().postDelayed({
            text_quiz_detail.text = getString(R.string.quiz_count_detail, 1)
            text_quiz_count.text = "1"
        }, 2000L)

        Handler().postDelayed({
            text_quiz_detail.text = getString(R.string.quiz_count_detail, 0)
            text_quiz_count.text = "0"

            ObjectAnimator.ofFloat(card_quiz_flip1, "scaleX", 1f, 0f).apply {
                interpolator = DecelerateInterpolator()
                duration = 500
                start()
            }

        }, 3000L)
    }
}
