package com.base.kotlin.data.api.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class NullOnEmptyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
//        return super.responseBodyConverter(type, annotations, retrofit)
        return NullOnEmptyResponseBodyConverter<Any>(this, type, annotations, retrofit)
    }
}