package depromeet.depton.nunayun.presentation

import depromeet.depton.nunayun.presentation.quiz.QuizViewModel
import depromeet.depton.nunayun.presentation.result.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuizViewModel() }
    viewModel { ResultViewModel() }
}