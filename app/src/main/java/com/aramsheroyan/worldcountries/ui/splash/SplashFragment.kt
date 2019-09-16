/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.splash

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.aramsheroyan.worldcountries.R
import com.aramsheroyan.worldcountries.app.WCApplication
import com.aramsheroyan.worldcountries.ui.quiz.QuizComponent
import com.aramsheroyan.worldcountries.ui.quiz.QuizModule
import kotlinx.android.synthetic.main.splash_fragment.*
import javax.inject.Inject

class SplashFragment : Fragment() {

    private var splashComponent: SplashComponent? = null

    @Inject
    lateinit var splashViewModelFactory: SplashViewModel.Factory

    private lateinit var viewModel: SplashViewModel
    private lateinit var runnable: Runnable
    private  var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        createSubcomponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this,splashViewModelFactory).get(SplashViewModel::class.java)
        viewModel.fetchCountries()
        viewModel.state.observe(this, Observer {

            when(it){
                SplashViewModel.State.OnSuccess->{
                    runnable = Runnable{
                        view.findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
                    }
                    handler.postDelayed(runnable,2000)
                }
                SplashViewModel.State.OnError->{
                    activity?.finish()
                }
            }
        })
    }


    private fun createSubcomponent(): SplashComponent? {
        splashComponent = (activity?.application as WCApplication)
            .component
            .createSubComponent()
        return splashComponent
    }

}
