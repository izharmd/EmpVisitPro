package com.jslps.empvisist.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonParser {
    private val gson = Gson()

    fun <T> fromJson(json: String, classOfT: Class<T>): T {
        return gson.fromJson(json, classOfT)
    }

    fun <T> fromJsonToList(json: String, classOfT: Class<T>): List<T> {
        return gson.fromJson(json, TypeToken.getParameterized(List::class.java, classOfT).type)
    }
}