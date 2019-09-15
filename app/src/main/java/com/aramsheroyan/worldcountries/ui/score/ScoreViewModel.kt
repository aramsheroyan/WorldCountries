package com.aramsheroyan.worldcountries.ui.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aramsheroyan.worldcountries.ui.quiz.TYPE_ALL
import com.aramsheroyan.worldcountries.ui.quiz.TYPE_CAPITALS_DAILY
import com.aramsheroyan.worldcountries.ui.quiz.TYPE_LEARNED
import io.reactivex.disposables.CompositeDisposable

class ScoreViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val actionCommand = MutableLiveData<Action>()

    sealed class Action {
        object RetryNewCountries : Action()
        object RetryLearnedCountries : Action()
        object Next : Action()
        object Finish : Action()
    }

    fun onScreenStarted(score: Int, quizType: String) {
        when (quizType) {
            TYPE_ALL -> {
                actionCommand.value = Action.Finish
            }
            TYPE_CAPITALS_DAILY -> {
                actionCommand.value = if (score == 100) Action.Next else Action.RetryNewCountries
            }
            TYPE_LEARNED->{
                actionCommand.value = if (score >= 75) Action.Finish else Action.RetryLearnedCountries
            }

        }
    }

    fun getActionCommand() = actionCommand


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
