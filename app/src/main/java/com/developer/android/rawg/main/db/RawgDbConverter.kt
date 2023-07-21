package com.developer.android.rawg.main.db

import androidx.room.TypeConverter
import com.developer.android.rawg.main.model.games.ParentPlatform
import com.developer.android.rawg.main.model.games.ParentPlatformContainer
import com.developer.android.rawg.main.model.games.ShortScreenshot
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject

class RawgDbConverter @Inject constructor() {

    private val gson: Gson = GsonBuilder().create()

    @TypeConverter
    fun fromListShortScreenshots(data: List<ShortScreenshot>?): String {
        val result = StringBuilder()
        data?.let {
            for (item in data) {
                result.append(gson.toJson(item))
                result.append(';')
            }
        }
        return result.toString()
    }

    @TypeConverter
    fun toListShortScreenshots(data: String): List<ShortScreenshot> {
        if (data.isEmpty()) return listOf()
        val result = mutableListOf<ShortScreenshot>()
        for (item in data.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            result.add(gson.fromJson(item, ShortScreenshot::class.java))
        }
        return result
    }

    @TypeConverter
    fun fromListParentPlatformContainer(data: List<ParentPlatformContainer>?): String {
        val result = StringBuilder()
        data?.let {
            for (item in data) {
                result.append(gson.toJson(item))
                result.append(';')
            }
        }
        return result.toString()
    }

    @TypeConverter
    fun toListParentPlatformContainer(data: String): List<ParentPlatformContainer> {
        if (data.isEmpty()) return listOf()
        val result = mutableListOf<ParentPlatformContainer>()
        for (item in data.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            result.add(gson.fromJson(item, ParentPlatformContainer::class.java))
        }
        return result
    }

    @TypeConverter
    fun fromParentPlatform(parentPlatform: ParentPlatform): String {
        return gson.toJson(parentPlatform)
    }

    @TypeConverter
    fun toParentPlatform(data: String): ParentPlatform {
        return gson.fromJson(data, ParentPlatform::class.java)
    }

}