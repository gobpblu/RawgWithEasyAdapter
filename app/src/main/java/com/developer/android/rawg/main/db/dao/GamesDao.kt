package com.developer.android.rawg.main.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.developer.android.rawg.main.db.entities.FullGameEntity

@Dao
interface GamesDao {

    @Query("SELECT * FROM games WHERE slug LIKE :genre LIMIT :limit OFFSET :offset")
    fun getGames(genre: String, limit: Int, offset: Int): List<FullGameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGames(genres: List<FullGameEntity>)

}