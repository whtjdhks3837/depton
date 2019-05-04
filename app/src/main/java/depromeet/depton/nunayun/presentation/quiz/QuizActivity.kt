package depromeet.depton.nunayun.presentation.quiz

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import depromeet.depton.nunayun.R
import depromeet.depton.nunayun.databinding.ActivityQuizBinding
import depromeet.depton.nunayun.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import android.view.animation.DecelerateInterpolator
import depromeet.depton.nunayun.presentation.result.ResultActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizActivity : BaseActivity<ActivityQuizBinding>() {
    override val resourceId: Int = R.layout.activity_quiz

    private val viewModel: QuizViewModel by viewModel()
    private var questionNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        bindView()
    }

    private fun bindView() {
        showFirst()
        card_quiz_flip2.visibility = View.INVISIBLE

        image_quiz_flip2_up.setOnClickListener {
            image_quiz_flip2_up_select.visibility = View.VISIBLE
            image_quiz_flip2_down_select.visibility = View.INVISIBLE
        }

        image_quiz_flip2_down.setOnClickListener {
            image_quiz_flip2_up_select.visibility = View.INVISIBLE
            image_quiz_flip2_down_select.visibility = View.VISIBLE
        }

        btn_quiz_exit.setOnClickListener {
            finish()
        }
    }

    private fun showFirst() {
        Handler().postDelayed({
            text_quiz_first_count.text = "2"
        }, 1000L)
        Handler().postDelayed({
            text_quiz_first_count.text = "1"
        }, 2000L)
        Handler().postDelayed({
            text_quiz_first_count.text = "0"
            group_quiz_first.visibility = View.INVISIBLE
            card_quiz_flip1.visibility = View.VISIBLE
            card_quiz_flip2.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(card_quiz_flip2, "scaleX", 1f, 0f).apply {
                interpolator = DecelerateInterpolator()
                duration = 200
                start()
            }
            showQuestion()
        }, 3000L)
    }

    private fun showQuestion() {
        text_quiz_title.text = getString(R.string.quiz_title_number, ++questionNumber)
        text_quiz_order.text = "$questionNumber/10"
        highlight_quiz_title.setImageResource(
            when (questionNumber % 3) {
                0 -> R.drawable.quiz_title_pk
                1 -> R.drawable.quiz_title_gr
                else -> R.drawable.quiz_title_yw
            }
        )
        group_quiz_question.visibility = View.VISIBLE

        ObjectAnimator.ofFloat(card_quiz_flip1, "scaleX", 0f, 1f).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 200
            start()
        }

        Handler().postDelayed({
            group_quiz_question.visibility = View.GONE
            showCountDown()
        }, 2000)
    }

    private fun showCountDown() {
        ObjectAnimator.ofFloat(card_quiz_flip1, "scaleX", 1f, 0f).apply {
            group_quiz_countdown.visibility = View.GONE
            interpolator = DecelerateInterpolator()
            duration = 200
            start()
        }

        showSelect()
    }

    private fun showSelect() {
        ObjectAnimator.ofFloat(card_quiz_flip2, "scaleX", 0f, 1f).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 200
            start()
        }
        pgbar_quiz.visibility = View.VISIBLE
        pgbar_quiz.progress = 100

        object : CountDownTimer(3000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                pgbar_quiz.progress = 100 - millisUntilFinished.toInt() / 30
            }

            override fun onFinish() {
                pgbar_quiz.visibility = View.GONE
                ObjectAnimator.ofFloat(card_quiz_flip2, "scaleX", 1f, 0f).apply {
                    interpolator = DecelerateInterpolator()
                    duration = 200
                    start()
                    image_quiz_flip2_up_select.visibility = View.INVISIBLE
                    image_quiz_flip2_down_select.visibility = View.INVISIBLE
                }
                if (questionNumber == 10) {
                    startActivity(ResultActivity.newIntent(this@QuizActivity))
                } else {
                    showQuestion()
                }
            }
        }.start()
    }
}
