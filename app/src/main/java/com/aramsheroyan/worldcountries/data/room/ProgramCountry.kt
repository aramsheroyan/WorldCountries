/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe

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

        @Query("UPDATE capitalsProgram SET status='completed' WHERE status='in-progress'")
        fun updateInProgressItems(): Completable

        @Query("SELECT * FROM capitalsProgram WHERE status='completed'")
        fun getAllCompleted(): Maybe<List<ProgramCountry>>

        @Insert
        fun insertAll(vararg country: ProgramCountry)

    }

}