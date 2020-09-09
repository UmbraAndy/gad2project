package com.example.gads2

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


const val GADS_BASE_URL = "https://gadsapi.herokuapp.com/"
const val LEARNER_ENDPOINT = "api/hours"
const val SKILL_IQ_ENDPOINT = "api/skilliq"



interface GadsService {
    @GET(LEARNER_ENDPOINT)
    suspend fun topLearners(): List<LearnerResponseItem>

    @GET(SKILL_IQ_ENDPOINT)
    suspend fun topSkillIQ(): List<SkillIQResponseItem>
}

data class LearnerResponseItem(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    val name: String
)

data class SkillIQResponseItem(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int
)

const val GOOGLE_FORM_BASE_URL = "https://docs.google.com/forms/d/e/"
interface  GoogleFormService{
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitProject(
        @Field("entry.1824927963") email:String,
        @Field("entry.1877115667") name:String,
        @Field("entry.2006916086") lastName:String,
        @Field("entry.284483984") projectLink:String,

    ): retrofit2.Response<String>
}
object CommonNetwork {

    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    private val gadsRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GADS_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttp)
        .build()

    private val googleFormRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GOOGLE_FORM_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(okHttp)
        .build()

    val gadsService: GadsService = gadsRetrofit.create(GadsService::class.java)
    val googleFormService: GoogleFormService = googleFormRetrofit.create(GoogleFormService::class.java)
}