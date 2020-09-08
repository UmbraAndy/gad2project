package com.example.gads2

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://gadsapi.herokuapp.com/"
const val LEARNER_ENDPOINT = "api/hours"
const val SKILL_IQ_ENDPOINT = "api/skilliq"

interface GadsService {
    @GET(LEARNER_ENDPOINT)
    suspend fun topLearners(): List<LearnerResponseItem>

    @GET(SKILL_IQ_ENDPOINT)
    suspend fun topSkillIQ(): List<SkillIQResponseItem>
}

//
//class LearnerResponse : ArrayList<LearnerResponseItem>()

data class LearnerResponseItem(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    val name: String
)


//class SkillIQResponse : ArrayList<SkillIQResponseItem>()

data class SkillIQResponseItem(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int
)

object CommonNetwork {

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    //    var listOfSkillIQ: Type = Types.newParameterizedType(
//        List::class.java,
//        SkillIQResponseItem::class.java
//    )
//    var jsonAdapterSkillIq: JsonAdapter<List<SkillIQResponseItem>> = moshi.adapter(listOfSkillIQ)
//
//    var listOfLearner: Type = Types.newParameterizedType(
//        List::class.java,
//        LearnerResponseItem::class.java
//    )
//    var jsonAdapterLearner: JsonAdapter<List<SkillIQResponseItem>> = moshi.adapter(listOfLearner)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttp)
        .build()

    val gadsService: GadsService = retrofit.create(GadsService::class.java)
}