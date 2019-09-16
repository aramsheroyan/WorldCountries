/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.domain.dailyprogram

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramDataContract
import com.aramsheroyan.worldcountries.data.room.Country
import com.aramsheroyan.worldcountries.data.room.ProgramCountry
import io.reactivex.Maybe

class DailyProgramUseCase(
    private val countriesRepository: CountriesDataContract.Repository,
    private val capitalsProgramRepository: CapitalsProgramDataContract.Repository

) : DailyProgramDomainContract.UseCase {
    override fun getDailyCountries(): Maybe<List<Country>> {

        return capitalsProgramRepository.getInProgressCountries().flatMap {
            if (it.isNotEmpty()) {
               countriesRepository.getCapitalsProgramInProgress()
            } else {
                countriesRepository.getDailyCountries()
                    .doAfterSuccess { it1 ->
                        val programCountries = mutableListOf<ProgramCountry>()
                        for (country in it1) {
                            programCountries.add(ProgramCountry(0, "in-progress", country.name))
                        }
                        capitalsProgramRepository.save(programCountries)
                    }
            }
        }
    }

    override fun getInProgressCountries(): Maybe<List<ProgramCountry>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

