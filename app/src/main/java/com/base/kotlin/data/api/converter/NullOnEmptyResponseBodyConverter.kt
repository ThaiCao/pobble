package com.base.kotlin.data.api.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class NullOnEmptyResponseBodyConverter<T>: Converter<ResponseBody, T> {
    private var factory: Converter.Factory? = null
    private var type: Type? = null
    private var annotation: Array<kotlin.Annotation>
    private var retrofit: Retrofit? = null

    constructor(factory: Converter.Factory, type: Type, annotation: Array<kotlin.Annotation>, retrofit: Retrofit ){
        this.annotation = annotation
        this.factory = factory
        this.type = type
        this.retrofit = retrofit
    }


    override fun convert(value: ResponseBody): T? {
        if(value.contentLength() == 0L){
            return null
        }
        return retrofit?.nextResponseBodyConverter<T>(factory, type, annotation)?.convert(value)
    }
}