/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.ui.quiz.TYPE
import com.aramsheroyan.worldcountries.ui.quiz.TYPE_ALL
import com.aramsheroyan.worldcountries.ui.quiz.TYPE_CAPITALS_DAILY
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tryAllCardView.setOnClickListener {
            view.findNavController()
                .navigate(
                    R.id.action_navigation_home_to_quizFragment,
                    bundleOf(TYPE to TYPE_ALL))
        }

        dailyProgramCardView.setOnClickListener {
            view.findNavController()
                .navigate(
                    R.id.action_navigation_home_to_dailyProgramFragment,
                    bundleOf(TYPE to TYPE_CAPITALS_DAILY))
        }
    }

}