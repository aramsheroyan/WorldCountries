/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.quiz

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramDataContract
import com.aramsheroyan.worldcountries.data.room.Country
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

const val DIRECT_ORDER = 0
const val REVERSE_ORDER = 1

class QuizPresenter(
    private val view: QuizPresentationContract.View,
    private val countriesRepository: CountriesDataContract.Repository,
    private val capitalsProgramRepository: CapitalsProgramDataContract.Repository
) : QuizPresentationContract.Presenter {

    private var countryPool = mutableListOf<Country>()
    private var countries = mutableListOf<Country>()
    private var allCountries = mutableListOf<Country>()


    private var name: String = ""
    private var type: String = ""
    private var correctAnswer: String = ""
    private var order: Int = 0

    private var correctAnswersCount: Int = 0

    private val compositeDisposable = CompositeDisposable()

    override fun onScreenStarted(type: String, order: Int) {
        this.type = type
        var observable: Maybe<List<Country>> = Maybe.just(listOf())
        when (type) {
            TYPE_ALL -> observable = countriesRepository.getRandom(30)
            TYPE_CAPITALS_DAILY -> observable = countriesRepository.getCapitalsProgramInProgress()
            TYPE_LEARNED -> observable = countriesRepository.getLearnedCountries(20)
        }

        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d(it.toString())
                    val shuffledList = it.shuffled()
                    countryPool.addAll(shuffledList)
                    countries.addAll(shuffledList)
                    this.order = order
                    getAllCountries()
                }, {
                    Timber.e(it)
                })

        )
    }

    override fun getNext() {
        if (countryPool.size > 0) {
            val country = countryPool[0]
            val optionsIndex = (0 until allCountries.size - 1).shuffled().take(4).toMutableList()
            for((indexValue, i) in optionsIndex.withIndex() ){
                if(country.name == allCountries[indexValue].name){
                    optionsIndex.removeAt(i)
                }
            }

            var options = mutableListOf<String>()
            when (order) {
                DIRECT_ORDER -> {
                    options = mutableListOf(
                        allCountries[optionsIndex[0]].capital!!,
                        allCountries[optionsIndex[1]].capital!!,
                        allCountries[optionsIndex[2]].capital!!,
                        country.capital!!
                    )
                    name = country.name!!
                    correctAnswer = country.capital
                }
                REVERSE_ORDER -> {
                    options = mutableListOf(
                        allCountries[optionsIndex[0]].name!!,
                        allCountries[optionsIndex[1]].name!!,
                        allCountries[optionsIndex[2]].name!!,
                        country.name!!
                    )
                    name = country.capital!!
                    correctAnswer = country.name
                }
            }
            countryPool.removeAt(0)
            val leftCount = "${countries.size - countryPool.size}/${countries.size}"
            view.setNext(
                name,
                correctAnswer,
                options.toList().shuffled(),
                leftCount
            )
        } else {
            val score = ((correctAnswersCount.toFloat() / countries.size.toFloat()) * 100).toInt()
            compositeDisposable.add(
                countriesRepository.getLearnedCountries(20)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        val skipLearnedCountries = it.isEmpty() && score == 100

                        if ((type == TYPE_LEARNED && score >= 75) || skipLearnedCountries) {
                            compositeDisposable.add(
                                capitalsProgramRepository.updateInProgressItems()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()
                            )
                        }

                        view.setCompleted(
                            score,
                            skipLearnedCountries
                        )
                    }, {
                        Timber.e(it)
                    })
            )
        }

    }

    override fun updateScore(wasCorrect: Boolean) {
        if (wasCorrect)
            correctAnswersCount += 1
    }

    override fun onDestroyed() {
        compositeDisposable.dispose()
    }

    private fun getAllCountries(){
        compositeDisposable.add(
            countriesRepository.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    allCountries.addAll(it.shuffled())
                    getNext()
                }, {
                    Timber.e(it)
                })

        )
    }


}
