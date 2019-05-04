package depromeet.depton.nunayun.presentation

import depromeet.depton.nunayun.presentation.home.HomeViewModel
import depromeet.depton.nunayun.presentation.login.LoginViewModel
import depromeet.depton.nunayun.presentation.quiz.QuizViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuizViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}