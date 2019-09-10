package com.aramsheroyan.worldcountries.ui.quiz

import com.aramsheroyan.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [QuizModule::class])
interface QuizComponent {
    fun inject(target: QuizFragment)
}