package com.example.worldcountries.ui.quiz

interface QuizPresentationContract {
    interface View {
        fun setNext(
            name: String,
            correctAnswer: String,
            options: List<String>
        )

        fun setCompleted(correctAnswerPercentage: Int)
    }

    interface Presenter {
        fun onScreenStarted(type: String, order: Int)
        fun getNext()
        fun updateScore(wasCorrect: Boolean)
        fun onDestroyed()
    }
}