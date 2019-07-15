package io.github.manuelernesto.mvvmapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.manuelernesto.mvvmapp.data.db.entities.CURRENT_USER_ID
import io.github.manuelernesto.mvvmapp.data.db.entities.Quote
import io.github.manuelernesto.mvvmapp.data.db.entities.User

@Dao
interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quotes: List<Quote>): Long

    @Query(value = "select * from quote")
    fun getQuotes(): LiveData<List<Quote>>
}