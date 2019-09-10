package com.aramsheroyan.worldcountries.ui.score

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.ui.quiz.TYPE
import com.aramsheroyan.worldcountries.ui.quiz.TYPE_LEARNED
import com.aramsheroyan.worldcountries.ui.score.ScoreViewModel.Action.*
import kotlinx.android.synthetic.main.score_fragment.*

const val SCORE = "score"

class ScoreFragment : Fragment() {
    private var type: String? = null
    private var score: Int = 0

    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.score_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Score"

        viewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        arguments?.let {
            type = it.getString(TYPE)
            score = it.getInt(SCORE)

        }

        if (type != null) {
            viewModel.onScreenStarted(score, type!!)
        }

        viewModel.getActionCommand().observe(this, Observer {
            handleActionCommand(it)
        })
    }

    private fun handleActionCommand(action: ScoreViewModel.Action) {
        when (action) {
            RetryNewCountries -> {
                actionButton.text = "Retry"
                scoreTextView.text = "$score%"
                detailsTextView.text = "You need to score 100% in order to continue"
                actionButton.setOnClickListener {
                    view?.findNavController()
                        ?.navigate(R.id.action_scoreFragment_to_quizFragment, bundleOf(TYPE to type))
                }
            }
            RetryLearnedCountries -> {
                actionButton.text = "Retry"
                scoreTextView.text = "$score%"
                detailsTextView.text =
                    "You need to score at least 75% on already learned countries in order complete the daily program"
                actionButton.setOnClickListener {
                    view?.findNavController()
                        ?.navigate(R.id.action_scoreFragment_to_quizFragment, bundleOf(TYPE to type))
                }
            }
            Next -> {
                actionButton.text = "Next"
                scoreTextView.text = "$score%"
                detailsTextView.text =
                    "Great! Lets looks at the counties that you've already learned. You need to score at least 75% to complete the daily program"
                actionButton.setOnClickListener {
                    view?.findNavController()
                        ?.navigate(R.id.action_scoreFragment_to_quizFragment, bundleOf(TYPE to TYPE_LEARNED))
                }
            }
            Finish -> {
                actionButton.text = "Finish"
                scoreTextView.text = "$score%"
                detailsTextView.text =
                    "Great job! You have finished today's program"
                actionButton.setOnClickListener {
                    view?.findNavController()
                        ?.navigate(R.id.action_scoreFragment_to_navigation_home)
                }
            }

        }
    }

}
