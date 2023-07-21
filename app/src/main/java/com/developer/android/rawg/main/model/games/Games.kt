package com.developer.android.rawg.main.model.games

import android.os.Parcelable
import com.developer.android.rawg.main.model.GameType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val count: Int,
    val next: String?,
    val previous: String?,
    val games: List<GameType>
    /*@SerializedName("seo_title")
    val seoTitle: String,
    @SerializedName("seo_description")
    val seoDescription: String,
    @SerializedName("seo_keywords")
    val seoKeywords: String,
    @SerializedName("seo_h1")
    val seoH1: String,
    @SerializedName("noindex")
    val noIndex: Boolean,
    @SerializedName("nofollow")
    val noFollow: Boolean,
    @SerializedName("description")
    val description: String,*/
) : Parcelable