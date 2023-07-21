package com.developer.android.rawg.main.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developer.android.rawg.main.model.genres.Genre

@Entity(tableName = "genres")
data class GenreEntity(
    val name: String,
    @PrimaryKey
    val slug: String,
) {

    fun toGenre() = Genre(
        name = name,
        slug = slug
    )

}
