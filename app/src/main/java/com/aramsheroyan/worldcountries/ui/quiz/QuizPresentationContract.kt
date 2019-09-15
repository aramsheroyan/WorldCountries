package com.aramsheroyan.worldcountries.ui.quiz

interface QuizPresentationContract {
    interface View {
        fun setNext(
            name: String,
            correctAnswer: String,
            options: List<String>,
            leftCount: String
        )

        fun setCompleted(score: Int, skipLearnedCountries: Boolean)
    }

    interface Presenter {
        fun onScreenStarted(type: String, order: Int)
        fun getNext()
        fun updateScore(wasCorrect: Boolean)
        fun onDestroyed()
    }
}