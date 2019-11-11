package com.base.kotlin.data.api.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonWriter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.Charset
import java.nio.Buffer as Buffer1

class GsonRequestBodyConverter<T>: Converter<T, RequestBody> {

    private val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!
    private val UTF_8: Charset = Charsets.UTF_8

    private var gson: Gson? = null
    private var adapter: TypeAdapter<T>? = null

    constructor(gson: Gson, adapter: TypeAdapter<T>){
        this.gson = gson
        this.adapter = adapter
    }


    override fun convert(value: T): RequestBody {
        var buffer = Buffer()
        var writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        var jsonWriter: JsonWriter = gson!!.newJsonWriter(writer)
        adapter?.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }
}