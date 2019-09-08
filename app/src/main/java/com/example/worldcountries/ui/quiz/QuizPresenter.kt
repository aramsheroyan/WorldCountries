package com.example.worldcountries.ui.quiz

import android.widget.Toast
import com.example.worldcountries.data.countries.CountriesDataContract
import com.example.worldcountries.data.room.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import kotlin.random.Random


const val DIRECT_ORDER = 0
const val REVERSE_ORDER = 1

class QuizPresenter(
    private val view: QuizPresentationContract.View,
    private val countriesRepository: CountriesDataContract.Repository
) : QuizPresentationContract.Presenter {

    private var countryPool = mutableListOf<Country>()
    private var countries = mutableListOf<Country>()

    private var name: String = ""
    private var correctAnswer: String = ""
    private var order: Int = 0

    private var correctAnswersCount: Int = 0

    private val compositeDisposable = CompositeDisposable()

    override fun onScreenStarted(type: String, order: Int) {
        compositeDisposable.add(
            countriesRepository.getRandom(30)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d(it.toString())
                    countryPool.addAll(it)
                    countries.addAll(it)
                    this.order = order
                    getNext()
                }, {
                    Timber.e(it)
                })
        )
    }

    override fun getNext() {
        if (countryPool.size > 0) {
            val country = countryPool[0]
            val excludeIndex = countries.size - countryPool.size
            val optionsIndex = (0 until countries.size - 1).shuffled().take(4).toMutableList()
            for (i in 0 until 3) {
                if (optionsIndex[i] == excludeIndex) {
                    optionsIndex.removeAt(i)
                }
            }

            var options = mutableListOf<String>()
            when (order) {
                DIRECT_ORDER -> {
                    options = mutableListOf(
                        countries[optionsIndex[0]].capital!!,
                        countries[optionsIndex[1]].capital!!,
                        countries[optionsIndex[2]].capital!!,
                        country.capital!!
                    )
                    name = country.name!!
                    correctAnswer = country.capital
                }
                REVERSE_ORDER -> {
                    options = mutableListOf(
                        countries[optionsIndex[0]].name!!,
                        countries[optionsIndex[1]].name!!,
                        countries[optionsIndex[2]].name!!,
                        country.name!!
                    )
                    name = country.capital!!
                    correctAnswer = country.name
                }
            }


            Timber.d("Exlude : $excludeIndex")
            Timber.d("OptionsIdex : $optionsIndex")

            Timber.d("Options : $options")
            Timber.d("Country : $country")
            Timber.d("CountrPooly : $countryPool")

            Timber.d("correct : $correctAnswersCount")

            countryPool.removeAt(0)
            view.setNext(name, correctAnswer, options.toList().shuffled())
        } else {
            view.setCompleted(((correctAnswersCount.toFloat() / countries.size.toFloat()) * 100).toInt())
        }


    }

    override fun updateScore(wasCorrect: Boolean) {
        if (wasCorrect)
            correctAnswersCount += 1
    }

    override fun onDestroyed() {
        compositeDisposable.dispose()
    }


}
