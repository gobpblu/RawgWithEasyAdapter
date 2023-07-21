package com.developer.android.rawg.main.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.developer.android.rawg.main.db.dao.GamesDao
import com.developer.android.rawg.main.db.dao.GenresDao
import com.developer.android.rawg.main.db.entities.FullGameEntity
import com.developer.android.rawg.main.db.entities.GenreEntity

@Database(version = 2, entities = [GenreEntity::class, FullGameEntity::class])
@TypeConverters(RawgDbConverter::class)
abstract class RawgDatabase : RoomDatabase() {
    abstract fun getGenresDao(): GenresDao
    abstract fun getGamesDao(): GamesDao
}