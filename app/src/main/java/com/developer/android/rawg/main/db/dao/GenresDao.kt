package com.developer.android.rawg.main.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.developer.android.rawg.main.db.entities.GenreEntity

@Dao
interface GenresDao {

    @Query("SELECT * FROM genres")
    fun getGenres(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGenres(genres: List<GenreEntity>)
}