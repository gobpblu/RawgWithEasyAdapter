package com.developer.android.rawg.main.model.genres

import android.os.Parcelable
import com.developer.android.rawg.common.mvp.Item
import com.developer.android.rawg.main.db.entities.GenreEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String,
    val slug: String,
) : Parcelable {

    fun toGenreEntity() = GenreEntity(
        name = name,
        slug = slug,
    )
}
