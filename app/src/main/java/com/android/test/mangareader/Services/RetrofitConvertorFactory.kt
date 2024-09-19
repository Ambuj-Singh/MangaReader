package com.android.test.mangareader.Services

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class RetrofitConvertorFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter {body -> if(body.contentLength() == 0L) null else delegate.convert(body) }
    }
}