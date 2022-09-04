package com.example.tracker.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.tracker.data.util.JsonParser
import com.example.tracker.domain.model.IssueLabel
import com.google.gson.reflect.TypeToken
import java.util.*

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    //Strings
    @TypeConverter
    fun fromStringsJson(json: String): List<String>{
        return jsonParser.fromJson<List<String>>(
            json,
            object: TypeToken<List<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toStringsJson(stringsList: List<String>): String{
        return jsonParser.toJson(
            stringsList,
            object: TypeToken<List<String>>(){}.type
        ) ?: "[]"
    }

    // Labels
    @TypeConverter
    fun fromLabelsJson(json: String): List<IssueLabel>{
        return jsonParser.fromJson<List<IssueLabel>>(
            json,
            object: TypeToken<List<IssueLabel>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toLabelsJson(stringsList: List<IssueLabel>): String{
        return jsonParser.toJson(
            stringsList,
            object: TypeToken<List<IssueLabel>>(){}.type
        ) ?: "[]"
    }

    //Dates
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

}