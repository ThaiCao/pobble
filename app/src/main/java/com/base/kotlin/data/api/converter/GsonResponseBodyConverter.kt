package com.base.kotlin.data.api.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.Exception

class GsonResponseBodyConverter<T> : Converter<ResponseBody, T> {
    private var gson: Gson? = null
    private var adapter:  TypeAdapter<T>? = null

    constructor(gson: Gson, adapter: TypeAdapter<T>){
        this.gson = gson
        this.adapter = adapter
    }

    override fun convert(value: ResponseBody): T? {
        return try{
            var jsonReader: JsonReader = gson!!.newJsonReader(value.charStream())
            adapter?.read(jsonReader)
        }catch (ex: Exception){
            null
        }finally {
            value.close()
        }
    }
}