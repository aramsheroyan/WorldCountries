package com.example.worldcountries.ui.quiz

import com.example.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [QuizModule::class])
interface QuizComponent {
    fun inject(target: QuizFragment)
}