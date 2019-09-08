package com.example.worldcountries.data.room

import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Entity(tableName = "capitalsProgram")
data class ProgramCountry(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "countryName") val name: String?
) {
    @Dao
    interface CapitalsProgramDAO {
        @Query("SELECT * FROM capitalsProgram WHERE status='in-progress'")
        fun getAllInProgressItems(): List<ProgramCountry>

        @Query("SELECT * FROM capitalsProgram WHERE status='completed'")
        fun getAllCompleted(): Maybe<List<ProgramCountry>>

        @Insert
        fun insertAll(vararg country: ProgramCountry)

    }

}