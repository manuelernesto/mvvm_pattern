package io.github.manuelernesto.mvvmapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.manuelernesto.mvvmapp.data.db.entities.CURRENT_USER_ID
import io.github.manuelernesto.mvvmapp.data.db.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User): Long

    @Query(value = "select * from user where uid =$CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}