package com.base.kotlin.data.api.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class GsonConverterFactory: Converter.Factory() {
    private var gson: Gson? = null

    private fun setGson(gson: Gson){
        this.gson = gson
    }
    companion object {
        public fun create() : GsonConverterFactory{
            return  create(Gson())
        }
        public fun create(gson: Gson): GsonConverterFactory{
            val converterFactory = GsonConverterFactory()
            converterFactory.gson = gson
            return converterFactory
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        //        return super.responseBodyConverter(type, annotations, retrofit)
        var adapter: TypeAdapter<out Any>? = gson!!.getAdapter(TypeToken.get(type))
        return GsonResponseBodyConverter<Any>(gson!!, adapter as TypeAdapter<Any>)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
//        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
        var adapter: TypeAdapter<out Any>? = gson!!.getAdapter(TypeToken.get(type))
        return GsonRequestBodyConverter<Any>(gson!!, adapter as TypeAdapter<Any>)
    }
}