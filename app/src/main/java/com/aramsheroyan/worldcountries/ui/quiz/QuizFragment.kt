/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.quiz


import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.app.WCApplication
import com.aramsheroyan.worldcountries.ui.score.SCORE
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_quiz.*
import javax.inject.Inject


const val TYPE = "TYPE"
const val ORDER = "ORDER"
const val TYPE_ALL = "all"
const val TYPE_CAPITALS_DAILY = "daily_capitals"
const val TYPE_LEARNED = "learned"

class QuizFragment : Fragment(), QuizPresentationContract.View {

    @Inject
    lateinit var presenter: QuizPresentationContract.Presenter

    private var quizComponent: QuizComponent? = null
    private var type: String? = null
    private var order: Int = 1
    private var correctAnswer: String? = null
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createSubcomponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        arguments?.let {
            type = it.getString(TYPE)
            order = it.getInt(ORDER)
        }

        if (type != null) {
            presenter.onScreenStarted(type!!, order)
        }
    }

    override fun onResume() {
        super.onResume()
        timer?.start()
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroyed()
    }

    override fun setNext(
        name: String,
        correctAnswer: String,
        options: List<String>,
        leftCount: String
    ) {
        this.correctAnswer = correctAnswer
        countTextView.text = leftCount
        nextButton.setOnClickListener(null)
        setButtonDisabled(nextButton)
        guessItemTextView.text = name
        optionOneButton.text = options[0]
        optionTwoButton.text = options[1]
        optionThreeButton.text = options[2]
        optionFourButton.text = options[3]
        setUpListeners()

        timer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                tearDownClickListeners()
                presenter.updateScore(false)
                presenter.getNext()
            }
        }.start()

    }

    override fun setCompleted(score: Int, skipLearnedCountries: Boolean) {
        if (skipLearnedCountries)
            type = TYPE_LEARNED

        view?.findNavController()?.navigate(
            R.id.action_quizFragment_to_scoreFragment,
            bundleOf(TYPE to type, SCORE to score)
        )
    }

    private fun setUpListeners() {
        optionOneButton.setOnClickListener {
            checkAnswer(optionOneButton)
        }
        optionTwoButton.setOnClickListener {
            checkAnswer(optionTwoButton)
        }
        optionThreeButton.setOnClickListener {
            checkAnswer(optionThreeButton)
        }
        optionFourButton.setOnClickListener {
            checkAnswer(optionFourButton)
        }
    }

    private fun checkAnswer(button: MaterialButton) {
        tearDownClickListeners()
        timer?.cancel()
        if (button.text.toString() == correctAnswer.toString()) {
            presenter.updateScore(true)
            setCorrect(button)
        } else {
            presenter.updateScore(false)
            setIncorrect(button)
            showCorrectAnswer()
        }

        setButtonEnabled(nextButton)
        nextButton.setOnClickListener {
            restoreButton(optionOneButton)
            restoreButton(optionTwoButton)
            restoreButton(optionThreeButton)
            restoreButton(optionFourButton)
            presenter.getNext()
        }
    }

    private fun showCorrectAnswer() {
        when (correctAnswer) {
            optionOneButton.text -> setCorrect(optionOneButton)
            optionTwoButton.text -> setCorrect(optionTwoButton)
            optionThreeButton.text -> setCorrect(optionThreeButton)
            optionFourButton.text -> setCorrect(optionFourButton)
        }
    }

    private fun tearDownClickListeners() {
        optionOneButton.setOnClickListener(null)
        optionTwoButton.setOnClickListener(null)
        optionThreeButton.setOnClickListener(null)
        optionFourButton.setOnClickListener(null)
    }

    private fun createSubcomponent(): QuizComponent? {
        quizComponent = (activity?.application as WCApplication)
            .component
            .createSubComponent(QuizModule(this))
        return quizComponent
    }

    private fun setCorrect(button: MaterialButton) {
        button.setOnClickListener(null)
        button.backgroundTintList = ContextCompat.getColorStateList(
            context!!,
            R.color.green
        )
        button.strokeColor = ContextCompat.getColorStateList(
            context!!,
            R.color.green
        )

        button.setTextColor(
            ContextCompat.getColorStateList(
                context!!,
                R.color.white
            )
        )
    }

    private fun setIncorrect(button: MaterialButton) {
        button.setOnClickListener(null)
        button.backgroundTintList = ContextCompat.getColorStateList(
            context!!,
            R.color.red
        )
        button.strokeColor = ContextCompat.getColorStateList(
            context!!,
            R.color.red
        )

        button.setTextColor(
            ContextCompat.getColorStateList(
                context!!,
                R.color.white
            )
        )
    }

    fun setButtonDisabled(button: MaterialButton) {
        button.backgroundTintList = ContextCompat.getColorStateList(
            context!!,
            R.color.light_gray
        )
        button.rippleColor = ContextCompat.getColorStateList(
            context!!,
            R.color.transparent
        )
    }

    private fun setButtonEnabled(button: MaterialButton) {
        button.backgroundTintList = ContextCompat.getColorStateList(
            context!!,
            R.color.white
        )

        button.rippleColor = ContextCompat.getColorStateList(
            context!!,
            R.color.holo_blue_light
        )
    }

    private fun restoreButton(button: MaterialButton) {
        button.backgroundTintList = ContextCompat.getColorStateList(
            context!!,
            R.color.white
        )
        button.strokeColor = ContextCompat.getColorStateList(
            context!!,
            R.color.colorPrimary
        )

        button.setTextColor(
            ContextCompat.getColorStateList(
                context!!,
                R.color.colorPrimary
            )
        )
    }


}
